select updateAllSchemaWithGivenQuery('
ALTER TABLE contentblock
	add column transparent boolean NOT NULL DEFAULT false;
');
