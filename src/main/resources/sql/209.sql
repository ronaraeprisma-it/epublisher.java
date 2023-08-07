select updateAllSchemaWithGivenQuery('
ALTER TABLE playlist
	ADD COLUMN state character varying(20) ,
    ADD COLUMN lastsubmittedby character varying(20) ,
	ADD COLUMN lastmodifiedby character varying(20) ;
	


');