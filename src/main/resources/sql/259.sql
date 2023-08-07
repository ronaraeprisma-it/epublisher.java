
--- Add extra columns to playlist
select updateAllSchemaWithGivenQuery('
	ALTER TABLE playlist
		ADD COLUMN createdBy character varying,
		ADD COLUMN createdDate TIMESTAMP(6) WITHOUT TIME ZONE;
');