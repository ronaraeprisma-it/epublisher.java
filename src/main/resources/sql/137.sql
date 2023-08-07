select  updateAllSchemaWithGivenQuery('
INSERT INTO outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelInternet''	,6,	''internet'',	1);

ALTER TABLE publication ADD COLUMN password VARCHAR;
ALTER TABLE publication ADD COLUMN username VARCHAR;

ALTER TABLE articleWrapper ADD COLUMN internet_id INTEGER;
ALTER TABLE articleWrapper ADD COLUMN removedfromedition boolean;
')

