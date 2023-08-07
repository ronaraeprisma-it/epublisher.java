select  updateAllSchemaWithGivenQuery('
CREATE TABLE route (id integer NOT NULL PRIMARY KEY, company varchar NOT NULL, route varchar NOT NULL,entityversion integer NOT NULL);
');

INSERT INTO World_Trade_Center.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('World_Trade_Center.epublisher_sequence'),	1,	'wtc-wayfinder',	'Wayfinder'	,null);

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 1);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'wayfinder');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 2);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'afbeelding');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

select  updateAllSchemaWithGivenQuery('
ALTER TABLE broadcast ADD COLUMN wayfinder boolean default false;
');

select  updateAllSchemaWithGivenQuery('
CREATE TABLE contentblock_route (contentblock_id integer NOT NULL, route_id integer NOT NULL,
CONSTRAINT contentblock_route_pkey PRIMARY KEY (contentblock_id, route_id),
  CONSTRAINT fk_epublisheruser_route_contentblockid FOREIGN KEY (contentblock_id)
      REFERENCES contentblock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_contentblock_route_id FOREIGN KEY (route_id)
      REFERENCES route (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION);
');

select  updateAllSchemaWithGivenQuery('
GRANT ALL ON TABLE route TO epublisher;
GRANT SELECT, REFERENCES ON TABLE route TO dashboard;
GRANT ALL ON TABLE contentblock_route TO epublisher;
GRANT SELECT, REFERENCES ON TABLE contentblock_route TO dashboard;
');

select  updateAllSchemaWithGivenQuery('
	INSERT INTO  outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelWayfinder'',8,''wayfinder'', 1);
');