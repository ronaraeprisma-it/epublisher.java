INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-fullscreen-image-frame',	'Full Screen Image + Frame'	,null);

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 2);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 3);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));




INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-landscape-image-text',	'Landscape Image + Text'	,null);

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 2);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));






INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-side-image-text',	'Side Image + Text'	,null);

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 2);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));







INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-doctor-image-text',	'Doctor Image + Text'	,null);

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 2);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 3);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));
