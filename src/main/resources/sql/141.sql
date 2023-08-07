------------------------ Portrait slide Bavo---------------------------

INSERT INTO Parnassia .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Parnassia .epublisher_sequence'),	1,	'bavo-portrait-slide',	'Portrait slide Bavo'	,null, null, '12 : 23');

INSERT INTO Parnassia .containerarea (id, entityversion, containerposition) VALUES (nextval('Parnassia .epublisher_sequence'), 1, 1);
INSERT INTO Parnassia .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Parnassia .containerarea),'tekst');
INSERT INTO Parnassia .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Parnassia .templatenarrowcasting),	(Select max(id) from Parnassia .containerarea));


INSERT INTO Parnassia .containerarea (id, entityversion, containerposition) VALUES (nextval('Parnassia .epublisher_sequence'), 1, 2);
INSERT INTO Parnassia .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Parnassia .containerarea),'afbeelding');
INSERT INTO Parnassia .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Parnassia .templatenarrowcasting),	(Select max(id) from Parnassia .containerarea));

------------------------Portrait slide Youz (copy to Parnassia client ticket SAAS-408)---------------------------

INSERT INTO Parnassia .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, imageaspectratio) VALUES ( nextval('Parnassia .epublisher_sequence'),	1,	'parnassia-youz-portrait-slide',	'Portrait slide Youz'	,null, '4:5');


INSERT INTO Parnassia .containerarea (id, entityversion, containerposition) VALUES (nextval('Parnassia .epublisher_sequence'), 1, 1);
INSERT INTO Parnassia .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Parnassia .containerarea),'tekst');
INSERT INTO Parnassia .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Parnassia .templatenarrowcasting),	(Select max(id) from Parnassia .containerarea));


INSERT INTO Parnassia .containerarea (id, entityversion, containerposition) VALUES (nextval('Parnassia .epublisher_sequence'), 1, 2);
INSERT INTO Parnassia .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Parnassia .containerarea),'afbeelding');
INSERT INTO Parnassia .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Parnassia .templatenarrowcasting),	(Select max(id) from Parnassia .containerarea));

------------------------ 2 Doctors SG template - Specialist voorstellen ---------------------------

INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, imageaspectratio) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-specialist-voorstellen',	'Specialist voorstellen'	,null, '1:1');

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 2);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 3);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 4);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 5);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 6);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 7);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'tekst');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 8);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));

