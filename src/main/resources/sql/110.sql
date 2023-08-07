select  updateAllSchemaWithGivenQuery('
ALTER TABLE templatenarrowcasting ADD COLUMN defaultimage_id INTEGER;
ALTER TABLE templatenarrowcasting ADD CONSTRAINT fkcb7d950ee67b987e FOREIGN KEY ("defaultimage_id") REFERENCES epublishermedia ("id");
');