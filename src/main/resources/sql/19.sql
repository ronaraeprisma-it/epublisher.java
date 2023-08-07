-- table for stroing resources for articles
CREATE TABLE resourceinfo
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  mimetype character varying(255),
  name character varying(255),
  reference character varying(255),
  resources_id integer,
  CONSTRAINT resourceinfo_pkey PRIMARY KEY (id),
  CONSTRAINT fkf2dd39fcbf65fbbe FOREIGN KEY (resources_id)
      REFERENCES article (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE resourceinfo
  OWNER TO nsliferay;
  
  