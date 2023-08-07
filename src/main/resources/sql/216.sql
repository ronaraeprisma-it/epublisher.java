select  updateAllSchemaWithGivenQuery('
CREATE TABLE available_publication_tabs (	
	id integer NOT NULL PRIMARY KEY,
 	entityversion integer NOT NULL,
	name character varying,
	type character varying);

CREATE TABLE available_publication_types (	
	id integer NOT NULL PRIMARY KEY,
	entityversion integer NOT NULL,
	dtype CHARACTER VARYING(31) NOT NULL);

CREATE TABLE publication_tabs (	
    publicationdtype_id integer NOT NULL,
    availabletab_id integer NOT NULL
);

ALTER TABLE publication_tabs 
	ADD CONSTRAINT fk_publicationdtype_id
	FOREIGN KEY ("publicationdtype_id") 
	REFERENCES available_publication_types ("id");

ALTER TABLE publication_tabs 
	ADD CONSTRAINT fk_availabletab_id
	FOREIGN KEY ("availabletab_id") 
	REFERENCES available_publication_tabs ("id");
');