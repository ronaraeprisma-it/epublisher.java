INSERT INTO World_Trade_Center.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('World_Trade_Center.epublisher_sequence'),	1,	'wtc-wayfinder-den-haag-links',	'Wayfinder Den Haag Links'	,null);
INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 1);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'wayfinder');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('World_Trade_Center.epublisher_sequence'),	1,	'wtc-wayfinder-den-haag-rechts',	'Wayfinder Den Haag Rechts'	,null);
INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 1);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'wayfinder');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('World_Trade_Center.epublisher_sequence'),	1,	'wtc-wayfinder-den-haag-single',	'Wayfinder Den Haag Single'	,null);
INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 1);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'wayfinder');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));