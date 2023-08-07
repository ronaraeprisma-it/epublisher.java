select  updateAllSchemaWithGivenQuery('
ALTER TABLE edition ADD column deleted boolean DEFAULT False ,ADD column  deletedDateTime  timestamp without time zone;
')