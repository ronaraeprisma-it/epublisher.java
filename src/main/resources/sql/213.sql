select updateAllSchemaWithGivenQuery('
	INSERT INTO permission (id, entityversion, name, nameid) VALUES (nextval(''epublisher_sequence''), 0, ''Indienen Playlist'', ''submit-playlist'');

	ALTER TABLE playlist ADD COLUMN broadcastsDifferentThanPublished boolean;
');