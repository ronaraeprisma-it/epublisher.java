select  updateAllSchemaWithGivenQuery('
	ALTER TABLE contentblock
	ADD COLUMN variant int;
')
