--------------------- add fields for video requirements  ------------------------

select updateAllSchemaWithGivenQuery('
	ALTER TABLE playlist ADD column shared boolean;
');
