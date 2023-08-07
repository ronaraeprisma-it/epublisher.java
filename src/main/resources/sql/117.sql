select  updateAllSchemaWithGivenQuery('
ALTER TABLE epublishermedia 
RENAME COLUMN uploaded TO mobileupload;
'); 