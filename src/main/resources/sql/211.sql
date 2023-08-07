select updateAllSchemaWithGivenQuery('
ALTER TABLE playlist
	ADD COLUMN parentid integer REFERENCES playlist(id);
');