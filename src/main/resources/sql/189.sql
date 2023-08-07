-- ns 2 kolom rechts template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-2kolomrechts',	'2 kolom rechts'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'twitter feed');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns fullscreen template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-fullscreen',	'full screen'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns fullscreen crisis template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-fullscreencrisis',	'full screen - crisis'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

-- ns live broadcast template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-livebroadcast',	'live broadcast'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain 2 kolommen template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-2-columns',	'NedTrain 2 kolommen'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 6);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

-- ns nedtrain attentie template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-attention',	'NedTrain - Attentie'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain fullscreen template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-fullscreen',	'NedTrain - full screen'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain landelijk template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-national',	'NedTrain - Landelijk'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 6);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain multipage template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-multipage',	'NedTrain - Multipage'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 6);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 7);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 8);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst+afb.');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 9);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 10);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'website');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 11);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain Overlijdens template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-comfort',	'NedTrain - Overlijdens'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain Regionaal template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-regional',	'NedTrain - Regionaal'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 6);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain Techniek template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-tech',	'NedTrain - Techniek'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 6);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'RSS');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns nedtrain Veiligheidsregels template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-nedtrain-safety',	'NedTrain - Veiligheidsregels'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'titel');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'telefoonnummer');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'datum');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));


-- ns traditioneel template
INSERT INTO NS.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('NS.epublisher_sequence'),	1,	'ns-traditioneel',	'traditioneel'	,null);

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 1);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 2);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 3);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 4);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 5);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'tekst');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'video');
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'afbeelding');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 6);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'klok');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));

INSERT INTO NS.containerarea (id, entityversion, containerposition) VALUES (nextval('NS.epublisher_sequence'), 1, 7);
INSERT INTO NS.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from NS.containerarea),'twitter feed');
INSERT INTO NS.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from NS.templatenarrowcasting),	(Select max(id) from NS.containerarea));