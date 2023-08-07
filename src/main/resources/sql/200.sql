select updateAllSchemaWithGivenQuery('
ALTER TABLE inputfield
	ADD COLUMN fieldPosition character varying(20) ;

ALTER TABLE inputfield
	ADD COLUMN separator boolean default false;
');