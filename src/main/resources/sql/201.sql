--paradigma new template - Tekst met foto rechts
INSERT INTO paradigma .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('paradigma .epublisher_sequence'),	1,	'paradigma-text-photo-rechts',	'Tekst met foto rechts'	,null, null, '8:9');

INSERT INTO paradigma .containerarea (id, entityversion, containerposition) VALUES (nextval('paradigma .epublisher_sequence'), 1, 1);
INSERT INTO paradigma .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from paradigma .containerarea),'tekst');
INSERT INTO paradigma .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from paradigma .templatenarrowcasting),	(Select max(id) from paradigma .containerarea));

INSERT INTO paradigma .containerarea (id, entityversion, containerposition) VALUES (nextval('paradigma .epublisher_sequence'), 1, 2);
INSERT INTO paradigma .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from paradigma .containerarea),'afbeelding');
INSERT INTO paradigma .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from paradigma .templatenarrowcasting),	(Select max(id) from paradigma .containerarea));

--paradigma new template - Tekst met foto Landscape
INSERT INTO paradigma .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('paradigma .epublisher_sequence'),	1,	'paradigma-text-photo-landscape',	'Tekst met foto landscape'	,null, null, '32:9');

INSERT INTO paradigma .containerarea (id, entityversion, containerposition) VALUES (nextval('paradigma .epublisher_sequence'), 1, 1);
INSERT INTO paradigma .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from paradigma .containerarea),'tekst');
INSERT INTO paradigma .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from paradigma .templatenarrowcasting),	(Select max(id) from paradigma .containerarea));

INSERT INTO paradigma .containerarea (id, entityversion, containerposition) VALUES (nextval('paradigma .epublisher_sequence'), 1, 2);
INSERT INTO paradigma .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from paradigma .containerarea),'afbeelding');
INSERT INTO paradigma .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from paradigma .templatenarrowcasting),	(Select max(id) from paradigma .containerarea));

--paradigma new template - Fullscreen Video met overlay tekst
INSERT INTO paradigma.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('paradigma.epublisher_sequence'),	1,	'video-fullscreen-text',	'Fullscreen video met overlay tekst'	,null, null, null);

INSERT INTO paradigma.containerarea (id, entityversion, containerposition) VALUES (nextval('paradigma.epublisher_sequence'), 1, 1);
INSERT INTO paradigma.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from paradigma.containerarea),'video');
INSERT INTO paradigma.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from paradigma.templatenarrowcasting),	(Select max(id) from paradigma.containerarea));

INSERT INTO paradigma .containerarea (id, entityversion, containerposition) VALUES (nextval('paradigma .epublisher_sequence'), 1, 2);
INSERT INTO paradigma .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from paradigma .containerarea),'tekst');
INSERT INTO paradigma .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from paradigma .templatenarrowcasting),	(Select max(id) from paradigma .containerarea));




--maatwerk new template - Tekst met foto rechts
UPDATE maatwerk .templatenarrowcasting SET name = 'Tekst met foto rechts' WHERE htmltemplate = 'maatwerk-text-photo-rechts';

--maatwerk new template - Tekst met foto Landscape
UPDATE maatwerk .templatenarrowcasting SET name = 'Tekst met foto landscape' WHERE htmltemplate = 'maatwerk-text-photo-landscape';

--maatwerk new template - Fullscreen Video met overlay tekst
UPDATE maatwerk .templatenarrowcasting SET name = 'Fullscreen video met overlay tekst' WHERE htmltemplate = 'video-fullscreen-text';



--ProRail new template - Tekst met foto rechts
INSERT INTO ProRail .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('ProRail .epublisher_sequence'),	1,	'prail-text-photo-rechts',	'Tekst met foto rechts'	,null, null, '8:9');

INSERT INTO ProRail .containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail .epublisher_sequence'), 1, 1);
INSERT INTO ProRail .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail .containerarea),'tekst');
INSERT INTO ProRail .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail .templatenarrowcasting),	(Select max(id) from ProRail .containerarea));

INSERT INTO ProRail .containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail .epublisher_sequence'), 1, 2);
INSERT INTO ProRail .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail .containerarea),'afbeelding');
INSERT INTO ProRail .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail .templatenarrowcasting),	(Select max(id) from ProRail .containerarea));

--ProRail new template - Tekst met foto Landscape
INSERT INTO ProRail .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('ProRail .epublisher_sequence'),	1,	'prail-text-photo-landscape',	'Tekst met foto landscape'	,null, null, '32:9');

INSERT INTO ProRail .containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail .epublisher_sequence'), 1, 1);
INSERT INTO ProRail .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail .containerarea),'tekst');
INSERT INTO ProRail .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail .templatenarrowcasting),	(Select max(id) from ProRail .containerarea));

INSERT INTO ProRail .containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail .epublisher_sequence'), 1, 2);
INSERT INTO ProRail .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail .containerarea),'afbeelding');
INSERT INTO ProRail .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail .templatenarrowcasting),	(Select max(id) from ProRail .containerarea));

--ProRail new template - Fullscreen Video met overlay tekst
INSERT INTO ProRail.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('ProRail.epublisher_sequence'),	1,	'video-fullscreen-text',	'Fullscreen video met overlay tekst'	,null, null, null);

INSERT INTO ProRail.containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail.epublisher_sequence'), 1, 1);
INSERT INTO ProRail.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail.containerarea),'video');
INSERT INTO ProRail.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail.templatenarrowcasting),	(Select max(id) from ProRail.containerarea));

INSERT INTO ProRail .containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail .epublisher_sequence'), 1, 2);
INSERT INTO ProRail .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail .containerarea),'tekst');
INSERT INTO ProRail .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail .templatenarrowcasting),	(Select max(id) from ProRail .containerarea));




--storeplay new template - Tekst met foto rechts
INSERT INTO storeplay .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('storeplay .epublisher_sequence'),	1,	'storeplay-text-photo-rechts',	'Tekst met foto rechts'	,null, null, '8:9');

INSERT INTO storeplay .containerarea (id, entityversion, containerposition) VALUES (nextval('storeplay .epublisher_sequence'), 1, 1);
INSERT INTO storeplay .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from storeplay .containerarea),'tekst');
INSERT INTO storeplay .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from storeplay .templatenarrowcasting),	(Select max(id) from storeplay .containerarea));

INSERT INTO storeplay .containerarea (id, entityversion, containerposition) VALUES (nextval('storeplay .epublisher_sequence'), 1, 2);
INSERT INTO storeplay .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from storeplay .containerarea),'afbeelding');
INSERT INTO storeplay .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from storeplay .templatenarrowcasting),	(Select max(id) from storeplay .containerarea));

--storeplay new template - Tekst met foto Landscape
INSERT INTO storeplay .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('storeplay .epublisher_sequence'),	1,	'storeplay-text-photo-landscape',	'Tekst met foto landscape'	,null, null, '32:9');

INSERT INTO storeplay .containerarea (id, entityversion, containerposition) VALUES (nextval('storeplay .epublisher_sequence'), 1, 1);
INSERT INTO storeplay .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from storeplay .containerarea),'tekst');
INSERT INTO storeplay .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from storeplay .templatenarrowcasting),	(Select max(id) from storeplay .containerarea));

INSERT INTO storeplay .containerarea (id, entityversion, containerposition) VALUES (nextval('storeplay .epublisher_sequence'), 1, 2);
INSERT INTO storeplay .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from storeplay .containerarea),'afbeelding');
INSERT INTO storeplay .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from storeplay .templatenarrowcasting),	(Select max(id) from storeplay .containerarea));

--storeplay new template - Fullscreen Video met overlay tekst
INSERT INTO storeplay.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('storeplay.epublisher_sequence'),	1,	'video-fullscreen-text',	'Fullscreen video met overlay tekst'	,null, null, null);

INSERT INTO storeplay.containerarea (id, entityversion, containerposition) VALUES (nextval('storeplay.epublisher_sequence'), 1, 1);
INSERT INTO storeplay.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from storeplay.containerarea),'video');
INSERT INTO storeplay.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from storeplay.templatenarrowcasting),	(Select max(id) from storeplay.containerarea));

INSERT INTO storeplay .containerarea (id, entityversion, containerposition) VALUES (nextval('storeplay .epublisher_sequence'), 1, 2);
INSERT INTO storeplay .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from storeplay .containerarea),'tekst');
INSERT INTO storeplay .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from storeplay .templatenarrowcasting),	(Select max(id) from storeplay .containerarea));




--zorgcentrale_noord new template - Tekst met foto rechts
INSERT INTO zorgcentrale_noord .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('zorgcentrale_noord .epublisher_sequence'),	1,	'zorgcentrale_noord-text-photo-rechts',	'Tekst met foto rechts'	,null, null, '8:9');

INSERT INTO zorgcentrale_noord .containerarea (id, entityversion, containerposition) VALUES (nextval('zorgcentrale_noord .epublisher_sequence'), 1, 1);
INSERT INTO zorgcentrale_noord .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord .containerarea),'tekst');
INSERT INTO zorgcentrale_noord .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord .templatenarrowcasting),	(Select max(id) from zorgcentrale_noord .containerarea));

INSERT INTO zorgcentrale_noord .containerarea (id, entityversion, containerposition) VALUES (nextval('zorgcentrale_noord .epublisher_sequence'), 1, 2);
INSERT INTO zorgcentrale_noord .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord .containerarea),'afbeelding');
INSERT INTO zorgcentrale_noord .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord .templatenarrowcasting),	(Select max(id) from zorgcentrale_noord .containerarea));

--zorgcentrale_noord new template - Tekst met foto Landscape
INSERT INTO zorgcentrale_noord .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('zorgcentrale_noord .epublisher_sequence'),	1,	'zorgcentrale_noord-text-photo-landscape',	'Tekst met foto landscape'	,null, null, '32:9');

INSERT INTO zorgcentrale_noord .containerarea (id, entityversion, containerposition) VALUES (nextval('zorgcentrale_noord .epublisher_sequence'), 1, 1);
INSERT INTO zorgcentrale_noord .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord .containerarea),'tekst');
INSERT INTO zorgcentrale_noord .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord .templatenarrowcasting),	(Select max(id) from zorgcentrale_noord .containerarea));

INSERT INTO zorgcentrale_noord .containerarea (id, entityversion, containerposition) VALUES (nextval('zorgcentrale_noord .epublisher_sequence'), 1, 2);
INSERT INTO zorgcentrale_noord .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord .containerarea),'afbeelding');
INSERT INTO zorgcentrale_noord .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord .templatenarrowcasting),	(Select max(id) from zorgcentrale_noord .containerarea));

--zorgcentrale_noord new template - Fullscreen Video met overlay tekst
INSERT INTO zorgcentrale_noord.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('zorgcentrale_noord.epublisher_sequence'),	1,	'video-fullscreen-text',	'Fullscreen video met overlay tekst'	,null, null, null);

INSERT INTO zorgcentrale_noord.containerarea (id, entityversion, containerposition) VALUES (nextval('zorgcentrale_noord.epublisher_sequence'), 1, 1);
INSERT INTO zorgcentrale_noord.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord.containerarea),'video');
INSERT INTO zorgcentrale_noord.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord.templatenarrowcasting),	(Select max(id) from zorgcentrale_noord.containerarea));

INSERT INTO zorgcentrale_noord .containerarea (id, entityversion, containerposition) VALUES (nextval('zorgcentrale_noord .epublisher_sequence'), 1, 2);
INSERT INTO zorgcentrale_noord .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord .containerarea),'tekst');
INSERT INTO zorgcentrale_noord .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord .templatenarrowcasting),	(Select max(id) from zorgcentrale_noord .containerarea));
