-- redesigned the filtering remove not used columns
ALTER TABLE subscriber DROP COLUMN businessunit;
ALTER TABLE subscriber DROP COLUMN companyfunction;
ALTER TABLE subscriber DROP COLUMN locationplace;
ALTER TABLE subscriber DROP COLUMN locationbuilding;

-- remove constraint - bug subscriptiongroupfilter_id should not be unique
ALTER TABLE subscriptiongroup_subscriptiongroupfilter DROP CONSTRAINT subscriptiongroup_subscriptiongroupfilter_filter_id_key;

-- table for storing filters relationship with subscribers 
CREATE TABLE subscriber_subscriptiongroupfilter
(
  subscriber_id integer NOT NULL,
  subscribersfilters_id integer NOT NULL,
  CONSTRAINT subscriber_subscriptiongroupfilter_pkey PRIMARY KEY (subscriber_id, subscribersfilters_id),
  CONSTRAINT fk27166aa3533d2c7b FOREIGN KEY (subscribersfilters_id)
      REFERENCES subscriptiongroupfilter (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk27166aa36683e2ec FOREIGN KEY (subscriber_id)
      REFERENCES subscriber (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE subscriber_subscriptiongroupfilter
  OWNER TO nsliferay;
