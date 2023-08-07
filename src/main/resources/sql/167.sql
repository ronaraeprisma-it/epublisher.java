--Viecuri template: Tekst + foto links
INSERT INTO viecuri .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('viecuri .epublisher_sequence'),	1,	'viecuri-text-photo-links',	'Text Photo Links'	,null, null, null);

INSERT INTO viecuri .containerarea (id, entityversion, containerposition) VALUES (nextval('viecuri .epublisher_sequence'), 1, 1);
INSERT INTO viecuri .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from viecuri .containerarea),'tekst');
INSERT INTO viecuri .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from viecuri .templatenarrowcasting),	(Select max(id) from viecuri .containerarea));

INSERT INTO viecuri .containerarea (id, entityversion, containerposition) VALUES (nextval('viecuri .epublisher_sequence'), 1, 2);
INSERT INTO viecuri .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from viecuri .containerarea),'afbeelding');
INSERT INTO viecuri .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from viecuri .templatenarrowcasting),	(Select max(id) from viecuri .containerarea));