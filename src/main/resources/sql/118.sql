INSERT INTO Zaansmedisch_Centrum .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Zaansmedisch_Centrum .epublisher_sequence'),	1,	'zmc-landscape-slide',	'Landscape slide'	,null, null, '9:4');


INSERT INTO Zaansmedisch_Centrum .containerarea (id, entityversion, containerposition) VALUES (nextval('Zaansmedisch_Centrum .epublisher_sequence'), 1, 1);
INSERT INTO Zaansmedisch_Centrum .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaansmedisch_Centrum .containerarea),'tekst');
INSERT INTO Zaansmedisch_Centrum .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaansmedisch_Centrum .templatenarrowcasting),	(Select max(id) from Zaansmedisch_Centrum .containerarea));


INSERT INTO Zaansmedisch_Centrum .containerarea (id, entityversion, containerposition) VALUES (nextval('Zaansmedisch_Centrum .epublisher_sequence'), 1, 2);
INSERT INTO Zaansmedisch_Centrum .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaansmedisch_Centrum .containerarea),'afbeelding');
INSERT INTO Zaansmedisch_Centrum .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaansmedisch_Centrum .templatenarrowcasting),	(Select max(id) from Zaansmedisch_Centrum .containerarea));