select  updateAllSchemaWithGivenQuery('
ALTER TABLE broadcast ADD COLUMN thumbnail_id INTEGER;
ALTER TABLE broadcast ADD CONSTRAINT fkcb7d1230f67b987e FOREIGN KEY ("thumbnail_id") REFERENCES epublishermedia ("id");
');