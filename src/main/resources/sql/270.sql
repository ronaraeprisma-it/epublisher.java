select updateAllSchemaWithGivenQuery('
	ALTER table publication ADD COLUMN uuid CHARACTER VARYING(255);
	ALTER table publication ADD COLUMN language CHARACTER VARYING(30);
');