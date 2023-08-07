INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Ziekenhuis_Amstelland.epublisher_sequence'),	1,	'amstelland-text-and-photo',	'Text + photo'	,null);

INSERT INTO Ziekenhuis_Amstelland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Amstelland.epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Amstelland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Amstelland.containerarea),'tekst');
INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Amstelland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Amstelland.containerarea));

INSERT INTO Ziekenhuis_Amstelland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Amstelland.epublisher_sequence'), 1, 2);
INSERT INTO Ziekenhuis_Amstelland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Amstelland.containerarea),'tekst');
INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Amstelland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Amstelland.containerarea));

INSERT INTO Ziekenhuis_Amstelland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Amstelland.epublisher_sequence'), 1, 3);
INSERT INTO Ziekenhuis_Amstelland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Amstelland.containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Amstelland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Amstelland.containerarea));

INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Ziekenhuis_Amstelland.epublisher_sequence'),	1,	'amstelland-text',	'Text'	,null);

INSERT INTO Ziekenhuis_Amstelland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Amstelland.epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Amstelland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Amstelland.containerarea),'tekst');
INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Amstelland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Amstelland.containerarea));

INSERT INTO Ziekenhuis_Amstelland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Amstelland.epublisher_sequence'), 1, 2);
INSERT INTO Ziekenhuis_Amstelland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Amstelland.containerarea),'tekst');
INSERT INTO Ziekenhuis_Amstelland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Amstelland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Amstelland.containerarea));
