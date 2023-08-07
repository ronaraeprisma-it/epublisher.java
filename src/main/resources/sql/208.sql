--centralpark new template - Tekst met foto rechts
INSERT INTO centralpark .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('centralpark .epublisher_sequence'),	1,	'centralpark-text-photo-rechts',	'Tekst met foto rechts'	,null, null, '8:9');

INSERT INTO centralpark .containerarea (id, entityversion, containerposition) VALUES (nextval('centralpark .epublisher_sequence'), 1, 1);
INSERT INTO centralpark .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from centralpark .containerarea),'tekst');
INSERT INTO centralpark .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from centralpark .templatenarrowcasting),	(Select max(id) from centralpark .containerarea));

INSERT INTO centralpark .containerarea (id, entityversion, containerposition) VALUES (nextval('centralpark .epublisher_sequence'), 1, 2);
INSERT INTO centralpark .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from centralpark .containerarea),'afbeelding');
INSERT INTO centralpark .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from centralpark .templatenarrowcasting),	(Select max(id) from centralpark .containerarea));

--centralpark new template - Tekst met foto Landscape
INSERT INTO centralpark .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('centralpark .epublisher_sequence'),	1,	'centralpark-text-photo-landscape',	'Tekst met foto landscape'	,null, null, '32:9');

INSERT INTO centralpark .containerarea (id, entityversion, containerposition) VALUES (nextval('centralpark .epublisher_sequence'), 1, 1);
INSERT INTO centralpark .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from centralpark .containerarea),'tekst');
INSERT INTO centralpark .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from centralpark .templatenarrowcasting),	(Select max(id) from centralpark .containerarea));

INSERT INTO centralpark .containerarea (id, entityversion, containerposition) VALUES (nextval('centralpark .epublisher_sequence'), 1, 2);
INSERT INTO centralpark .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from centralpark .containerarea),'afbeelding');
INSERT INTO centralpark .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from centralpark .templatenarrowcasting),	(Select max(id) from centralpark .containerarea));

--centralpark new template - Fullscreen Video met overlay tekst
INSERT INTO centralpark.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('centralpark.epublisher_sequence'),	1,	'video-fullscreen-text',	'Fullscreen video met overlay tekst'	,null, null, null);

INSERT INTO centralpark.containerarea (id, entityversion, containerposition) VALUES (nextval('centralpark.epublisher_sequence'), 1, 1);
INSERT INTO centralpark.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from centralpark.containerarea),'video');
INSERT INTO centralpark.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from centralpark.templatenarrowcasting),	(Select max(id) from centralpark.containerarea));

INSERT INTO centralpark .containerarea (id, entityversion, containerposition) VALUES (nextval('centralpark .epublisher_sequence'), 1, 2);
INSERT INTO centralpark .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from centralpark .containerarea),'tekst');
INSERT INTO centralpark .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from centralpark .templatenarrowcasting),	(Select max(id) from centralpark .containerarea));
