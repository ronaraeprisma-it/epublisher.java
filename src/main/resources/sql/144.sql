select  updateAllSchemaWithGivenQuery('
CREATE TABLE usertemplateapps (
	id VARCHAR NOT NULL, 
	type character varying(75),
    name character varying(75),
    websitelink character varying(75),
    previewimage character varying(255),
	duration integer,
	CONSTRAINT pk_templateapp PRIMARY KEY (id)
);


ALTER TABLE broadcast 
	ADD COLUMN "app_id" VARCHAR DEFAULT null;

ALTER TABLE broadcast 
	ADD CONSTRAINT fk_uta_ta_fkey 
	FOREIGN KEY ("app_id") 
	REFERENCES usertemplateapps ("id");
');