------------------------Slide Portrait-Left Image ---------------------------

INSERT INTO alrijne_ziekenhuis .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('alrijne_ziekenhuis .epublisher_sequence'),	1,	'slide_portrait_image_left',	'Slide + Portrait Image Left'	,null, null, '31:28');


INSERT INTO alrijne_ziekenhuis .containerarea (id, entityversion, containerposition) VALUES (nextval('alrijne_ziekenhuis .epublisher_sequence'), 1, 1);
INSERT INTO alrijne_ziekenhuis .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from alrijne_ziekenhuis .containerarea),'tekst');
INSERT INTO alrijne_ziekenhuis .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from alrijne_ziekenhuis .templatenarrowcasting),	(Select max(id) from alrijne_ziekenhuis .containerarea));


INSERT INTO alrijne_ziekenhuis .containerarea (id, entityversion, containerposition) VALUES (nextval('alrijne_ziekenhuis .epublisher_sequence'), 1, 2);
INSERT INTO alrijne_ziekenhuis .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from alrijne_ziekenhuis .containerarea),'afbeelding');
INSERT INTO alrijne_ziekenhuis .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from alrijne_ziekenhuis .templatenarrowcasting),	(Select max(id) from alrijne_ziekenhuis .containerarea));


------------------------Slide Portrait-Right Image ---------------------------

INSERT INTO alrijne_ziekenhuis .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('alrijne_ziekenhuis .epublisher_sequence'),	1,	'slide_portrait_image_right',	'Slide + Portrait Image Right'	,null, null, '57:55');


INSERT INTO alrijne_ziekenhuis .containerarea (id, entityversion, containerposition) VALUES (nextval('alrijne_ziekenhuis .epublisher_sequence'), 1, 1);
INSERT INTO alrijne_ziekenhuis .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from alrijne_ziekenhuis .containerarea),'tekst');
INSERT INTO alrijne_ziekenhuis .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from alrijne_ziekenhuis .templatenarrowcasting),	(Select max(id) from alrijne_ziekenhuis .containerarea));


INSERT INTO alrijne_ziekenhuis .containerarea (id, entityversion, containerposition) VALUES (nextval('alrijne_ziekenhuis .epublisher_sequence'), 1, 2);
INSERT INTO alrijne_ziekenhuis .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from alrijne_ziekenhuis .containerarea),'afbeelding');
INSERT INTO alrijne_ziekenhuis .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from alrijne_ziekenhuis .templatenarrowcasting),	(Select max(id) from alrijne_ziekenhuis .containerarea));