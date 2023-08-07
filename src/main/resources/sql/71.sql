TRUNCATE  epublisher.outputchannel CASCADE;
select  updateAllSchemaWithGivenQuery('INSERT INTO outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelNewsletterNS''	,2,	''newsletterNS'',	1);
INSERT INTO outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelIntranetNS''	,1,	''intranetNS'',	1);
INSERT INTO outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelPocketRailNS''	,3,	''railpocketNS'',	1);')

