------------------------Landscape slide---------------------------

INSERT INTO Ziekenhuis_Tjongerschans .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, imageaspectratio) VALUES ( nextval('Ziekenhuis_Tjongerschans .epublisher_sequence'),	1,	'tjs-landscape-image-slide',	'New landscape slide'	,null , '4/1' );


INSERT INTO Ziekenhuis_Tjongerschans .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Tjongerschans .epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Tjongerschans .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Tjongerschans .containerarea),'tekst');
INSERT INTO Ziekenhuis_Tjongerschans .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Tjongerschans .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Tjongerschans .containerarea));


INSERT INTO Ziekenhuis_Tjongerschans .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Tjongerschans .epublisher_sequence'), 1, 2);
INSERT INTO Ziekenhuis_Tjongerschans .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Tjongerschans .containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Tjongerschans .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Tjongerschans .templatenarrowcasting),	(Select max(id) from Ziekenhuis_Tjongerschans .containerarea));


INSERT INTO Ziekenhuis_Tjongerschans .containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Tjongerschans .epublisher_sequence'), 1, 3);
INSERT INTO Ziekenhuis_Tjongerschans .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Tjongerschans .containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Tjongerschans .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select id from Ziekenhuis_Tjongerschans .templatenarrowcasting where htmltemplate = 'tjs-landscape-image-slide'),	(Select max(id) from Ziekenhuis_Tjongerschans .containerarea));

