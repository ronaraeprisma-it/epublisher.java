------------------------ Portrait slide---------------------------

INSERT INTO Regionale_Uitvoeringsdienst .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Regionale_Uitvoeringsdienst .epublisher_sequence'),	1,	'rud-portrait-slide-or',	'Portrait slide OR'	,null, null, '85:92');


INSERT INTO Regionale_Uitvoeringsdienst .containerarea (id, entityversion, containerposition) VALUES (nextval('Regionale_Uitvoeringsdienst .epublisher_sequence'), 1, 1);
INSERT INTO Regionale_Uitvoeringsdienst .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Regionale_Uitvoeringsdienst .containerarea),'tekst');
INSERT INTO Regionale_Uitvoeringsdienst .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Regionale_Uitvoeringsdienst .templatenarrowcasting),	(Select max(id) from Regionale_Uitvoeringsdienst .containerarea));


INSERT INTO Regionale_Uitvoeringsdienst .containerarea (id, entityversion, containerposition) VALUES (nextval('Regionale_Uitvoeringsdienst .epublisher_sequence'), 1, 2);
INSERT INTO Regionale_Uitvoeringsdienst .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Regionale_Uitvoeringsdienst .containerarea),'afbeelding');
INSERT INTO Regionale_Uitvoeringsdienst .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Regionale_Uitvoeringsdienst .templatenarrowcasting),	(Select max(id) from Regionale_Uitvoeringsdienst .containerarea));

------------------------Slide + full text---------------------------

INSERT INTO Regionale_Uitvoeringsdienst .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Regionale_Uitvoeringsdienst .epublisher_sequence'),	1,	'rud-slide-full-text-or',	'Full Text slide OR'	,null, null);


INSERT INTO Regionale_Uitvoeringsdienst .containerarea (id, entityversion, containerposition) VALUES (nextval('Regionale_Uitvoeringsdienst .epublisher_sequence'), 1, 1);
INSERT INTO Regionale_Uitvoeringsdienst .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Regionale_Uitvoeringsdienst .containerarea),'tekst');
INSERT INTO Regionale_Uitvoeringsdienst .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Regionale_Uitvoeringsdienst .templatenarrowcasting),	(Select max(id) from Regionale_Uitvoeringsdienst .containerarea));
