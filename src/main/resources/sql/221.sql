select updateAllSchemaWithGivenQuery('
INSERT INTO outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelPortalPage'',10,''portalpage'', 1);
ALTER TABLE category ADD COLUMN active boolean DEFAULT true;
');