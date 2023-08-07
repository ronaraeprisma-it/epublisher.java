INSERT INTO Zorggroep_Almere.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, imageaspectratio) VALUES ( nextval('Zorggroep_Almere.epublisher_sequence'),	1,	'zga-partners-slides',	'Partners slides'	,null, '1:1');

INSERT INTO Zorggroep_Almere .containerarea (id, entityversion, containerposition) VALUES (nextval('Zorggroep_Almere .epublisher_sequence'), 1, 1);
INSERT INTO Zorggroep_Almere .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zorggroep_Almere .containerarea),'tekst');
INSERT INTO Zorggroep_Almere .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zorggroep_Almere .templatenarrowcasting),	(Select max(id) from Zorggroep_Almere .containerarea));

INSERT INTO Zorggroep_Almere .containerarea (id, entityversion, containerposition) VALUES (nextval('Zorggroep_Almere .epublisher_sequence'), 1, 2);
INSERT INTO Zorggroep_Almere .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zorggroep_Almere .containerarea),'afbeelding');
INSERT INTO Zorggroep_Almere .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zorggroep_Almere .templatenarrowcasting),	(Select max(id) from Zorggroep_Almere .containerarea));

INSERT INTO Zorggroep_Almere .containerarea (id, entityversion, containerposition) VALUES (nextval('Zorggroep_Almere .epublisher_sequence'), 1, 3);
INSERT INTO Zorggroep_Almere .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zorggroep_Almere .containerarea),'afbeelding');
INSERT INTO Zorggroep_Almere .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select id from Zorggroep_Almere .templatenarrowcasting where htmltemplate = 'zga-partners-slides'),	(Select max(id) from Zorggroep_Almere .containerarea));
