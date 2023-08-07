select updateAllSchemaWithGivenQuery('
	ALTER TABLE broadcast 
	ADD COLUMN title varchar(255);
');
