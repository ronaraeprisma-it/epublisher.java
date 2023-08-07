-- add state the group subscriber  
ALTER TABLE subscriptiongroup_subscriber ADD COLUMN subscriberstate character varying(255) NOT NULL DEFAULT 'ACTIVE';

-- extend subscriber with dtype and additional methods
ALTER TABLE subscriber ADD COLUMN dtype character varying(31) NOT NULL DEFAULT 'Subscriber';
ALTER TABLE subscriber ADD COLUMN origin character varying(255) NOT NULL DEFAULT 'INTERNAL';
ALTER TABLE subscriber ADD COLUMN businessunit character varying(255);
ALTER TABLE subscriber ADD COLUMN companyfunction character varying(255);
ALTER TABLE subscriber ADD COLUMN locationplace character varying(255);
ALTER TABLE subscriber ADD COLUMN locationbuilding character varying(255);
ALTER TABLE subscriber ADD COLUMN externidentifier character varying(255);

-- need to search on external id to update/delete users
CREATE INDEX subscriber_externidentifier_idx ON subscriber (externidentifier);

-- table for storing filter definitions for subscription groups
CREATE TABLE subscriptiongroupfilter
( 
  id integer NOT NULL,
  filtercode character varying(255) NOT NULL, 
  filtertype character varying(255) NOT NULL,
  filtervalue character varying(255),
  entityversion integer NOT NULL DEFAULT 1,
  CONSTRAINT subscriptiongroupfilter_pkey PRIMARY KEY (id) 
 )
WITH (
  OIDS=FALSE
);
ALTER TABLE subscriptiongroupfilter
  OWNER TO nsliferay;


-- table for storing filters relationship with subscription group  
CREATE TABLE subscriptiongroup_subscriptiongroupfilter
(
  subscriptiongroup_id integer NOT NULL,
  subscriptiongroupfilter_id integer NOT NULL,  
  CONSTRAINT subscriptiongroup_subscriptiongroupfilter_pkey PRIMARY KEY (subscriptiongroup_id, subscriptiongroupfilter_id),
  CONSTRAINT sg_sf_s_sid_fkey FOREIGN KEY (subscriptiongroupfilter_id)
      REFERENCES subscriptiongroupfilter (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sg_s_sg_sgid_fkey FOREIGN KEY (subscriptiongroup_id)
      REFERENCES subscriptiongroup (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT subscriptiongroup_subscriptiongroupfilter_filter_id_key UNIQUE (subscriptiongroupfilter_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE subscriptiongroup_subscriptiongroupfilter
  OWNER TO nsliferay;


