select updateAllSchemaWithGivenQuery('
ALTER TABLE IF EXISTS epublisheruser_allowedbrightcovefolders RENAME COLUMN allowedbrightcovefolders TO allowedvideofolders;
ALTER TABLE IF EXISTS epublisheruser_allowedbrightcovefolders RENAME TO epublisheruser_allowedvideofolders;
');