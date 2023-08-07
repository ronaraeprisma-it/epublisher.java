select  updateAllSchemaWithGivenQuery('ALTER TABLE template ADD column includeShareButton boolean DEFAULT False;

ALTER TABLE articlewrapper ADD column tinyUrl varchar(255);
GRANT UPDATE ON articlewrapper TO dashboard;
');