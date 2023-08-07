select updateAllSchemaWithGivenQuery('
	ALTER table playlist ADD COLUMN uuid CHARACTER VARYING(255);
	ALTER table contentblock ADD COLUMN fileuuid CHARACTER VARYING(255);
	ALTER table broadcast ADD COLUMN uuid CHARACTER VARYING(255);
');