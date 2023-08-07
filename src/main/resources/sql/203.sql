INSERT INTO schema_base.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('schema_base.epublisher_sequence'),	1,	'video-fullscreen-text',	'Video-Fullscreen-Text'	,null, null, null);
INSERT INTO schema_base.containerarea (id, entityversion, containerposition) VALUES (nextval('schema_base.epublisher_sequence'), 1, 1);
INSERT INTO schema_base.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from schema_base.containerarea),'video');
INSERT INTO schema_base.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from schema_base.templatenarrowcasting),	(Select max(id) from schema_base.containerarea));
INSERT INTO schema_base .containerarea (id, entityversion, containerposition) VALUES (nextval('schema_base .epublisher_sequence'), 1, 2);
INSERT INTO schema_base .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from schema_base .containerarea),'tekst');
INSERT INTO schema_base .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from schema_base .templatenarrowcasting),	(Select max(id) from schema_base .containerarea));


--schema_base template: Tekst + foto right
INSERT INTO schema_base .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('schema_base .epublisher_sequence'),	1,	'text-photo-rechts',	'Text Photo Rechts'	,null, null, '8:9');
INSERT INTO schema_base .containerarea (id, entityversion, containerposition) VALUES (nextval('schema_base .epublisher_sequence'), 1, 1);
INSERT INTO schema_base .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from schema_base .containerarea),'tekst');
INSERT INTO schema_base .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from schema_base .templatenarrowcasting),	(Select max(id) from schema_base .containerarea));
INSERT INTO schema_base .containerarea (id, entityversion, containerposition) VALUES (nextval('schema_base .epublisher_sequence'), 1, 2);
INSERT INTO schema_base .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from schema_base .containerarea),'afbeelding');
INSERT INTO schema_base .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from schema_base .templatenarrowcasting),	(Select max(id) from schema_base .containerarea));

--schema_base template: Tekst + foto landscape
INSERT INTO schema_base .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('schema_base .epublisher_sequence'),	1,	'text-photo-landscape',	'Text Photo Landscape'	,null, null, '32:9');
INSERT INTO schema_base .containerarea (id, entityversion, containerposition) VALUES (nextval('schema_base .epublisher_sequence'), 1, 1);
INSERT INTO schema_base .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from schema_base .containerarea),'tekst');
INSERT INTO schema_base .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from schema_base .templatenarrowcasting),	(Select max(id) from schema_base .containerarea));
INSERT INTO schema_base .containerarea (id, entityversion, containerposition) VALUES (nextval('schema_base .epublisher_sequence'), 1, 2);
INSERT INTO schema_base .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from schema_base .containerarea),'afbeelding');
INSERT INTO schema_base .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from schema_base .templatenarrowcasting),	(Select max(id) from schema_base .containerarea));