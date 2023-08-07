
select updateAllSchemaWithGivenQuery('

GRANT UPDATE ON edition TO dashboard;
GRANT UPDATE ON articlewrapper TO dashboard;


ALTER TABLE publication ADD COLUMN sharepointlocation varchar(255);
ALTER TABLE publication ADD COLUMN applocation varchar(255);

CREATE TABLE filter
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  name character varying(255) NOT NULL,
  active boolean NOT NULL,
  userid integer NOT NULL,
  pagetype character varying(255) NOT NULL,
  CONSTRAINT filter_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE filter
  OWNER TO epublisher;

GRANT ALL ON TABLE filter TO epublisher;
GRANT SELECT ON TABLE filter TO dashboard;
GRANT SELECT ON TABLE filter TO development;


CREATE TABLE filter_publication
(
  filter_id integer NOT NULL,
  publications_id integer NOT NULL,
  
  CONSTRAINT filter_publication_pkey PRIMARY KEY (filter_id, publications_id),
  CONSTRAINT filter_publication_fkey FOREIGN KEY (filter_id)
      REFERENCES filter (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT publication_filter_fkey FOREIGN KEY (publications_id)
      REFERENCES publication (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);


ALTER TABLE filter_publication
  OWNER TO epublisher;

GRANT ALL ON TABLE filter_publication TO epublisher;
GRANT SELECT ON TABLE filter_publication TO dashboard;
GRANT SELECT ON TABLE filter_publication TO development;


');
