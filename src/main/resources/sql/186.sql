select updateAllSchemaWithGivenQuery('
ALTER TABLE template ADD column campaignid integer;
ALTER TABLE template ADD column utm_content varchar(255);
ALTER TABLE template ADD column pk_keyword varchar(255);
');