-- table for storing publication groups
CREATE TABLE publication_group
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  apiid integer,
  name character varying(255) NOT NULL,
  CONSTRAINT publication_group_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE publication_group
  OWNER TO nsliferay;
  

-- table for linking publication groups with epublishermedia(images)   
CREATE TABLE publication_group_epublishermedia
(
  publication_group_id integer NOT NULL,
  images_id integer NOT NULL,
  CONSTRAINT publication_group_epublishermedia_pkey PRIMARY KEY (publication_group_id, images_id),
  CONSTRAINT fkf32b535af027fb4e FOREIGN KEY (publication_group_id)
      REFERENCES publication_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT publication_group_epublishermedia_images_id_key UNIQUE (images_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE publication_group_epublishermedia
  OWNER TO nsliferay;

  
-- table for linking publication groups with publications   
CREATE TABLE publication_group_publication
(
  publication_group_id integer NOT NULL,
  publications_id integer NOT NULL,
  CONSTRAINT publication_group_publication_pkey PRIMARY KEY (publication_group_id, publications_id),
  CONSTRAINT fk900b0919b1958e92 FOREIGN KEY (publications_id)
      REFERENCES publication (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk900b0919f027fb4e FOREIGN KEY (publication_group_id)
      REFERENCES publication_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE publication_group_publication
  OWNER TO nsliferay;  
  
  
-- migrating apiid from publication to publication group
-- 1. create publication group fromo publication use name apiid from publication
-- 2. insert relationship between publication and publication group
-- 3. drop column apiid from publication
INSERT INTO publication_group(id, entityversion, apiid, name)
SELECT row_number() OVER (), 0, p.apiid, p.name
  FROM publication as p
  where p.apiid IS NOT NULL and p.apiid >0;

INSERT INTO publication_group_publication(
            publication_group_id, publications_id)    
SELECT pg.id, (select p.id from publication as p where pg.apiid = p.apiid)
  FROM publication_group pg;

ALTER TABLE publication DROP COLUMN apiid; 