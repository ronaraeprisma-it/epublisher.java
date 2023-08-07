--photo links
INSERT INTO olvg_amsterdam .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('olvg_amsterdam .epublisher_sequence'),	1,	'olvg-photo-links-template',	'Photo Links Template'	,null, null, '1:1');

INSERT INTO olvg_amsterdam .containerarea (id, entityversion, containerposition) VALUES (nextval('olvg_amsterdam .epublisher_sequence'), 1, 1);
INSERT INTO olvg_amsterdam .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from olvg_amsterdam .containerarea),'tekst');
INSERT INTO olvg_amsterdam .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from olvg_amsterdam .templatenarrowcasting),	(Select max(id) from olvg_amsterdam .containerarea));

INSERT INTO olvg_amsterdam .containerarea (id, entityversion, containerposition) VALUES (nextval('olvg_amsterdam .epublisher_sequence'), 1, 2);
INSERT INTO olvg_amsterdam .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from olvg_amsterdam .containerarea),'afbeelding');
INSERT INTO olvg_amsterdam .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from olvg_amsterdam .templatenarrowcasting),	(Select max(id) from olvg_amsterdam .containerarea));


--photo rechts
INSERT INTO olvg_amsterdam .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('olvg_amsterdam .epublisher_sequence'),	1,	'olvg-photo-rechts-template',	'Photo Rechts Template'	,null, null, '1:1');

INSERT INTO olvg_amsterdam .containerarea (id, entityversion, containerposition) VALUES (nextval('olvg_amsterdam .epublisher_sequence'), 1, 1);
INSERT INTO olvg_amsterdam .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from olvg_amsterdam .containerarea),'tekst');
INSERT INTO olvg_amsterdam .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from olvg_amsterdam .templatenarrowcasting),	(Select max(id) from olvg_amsterdam .containerarea));

INSERT INTO olvg_amsterdam .containerarea (id, entityversion, containerposition) VALUES (nextval('olvg_amsterdam .epublisher_sequence'), 1, 2);
INSERT INTO olvg_amsterdam .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from olvg_amsterdam .containerarea),'afbeelding');
INSERT INTO olvg_amsterdam .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from olvg_amsterdam .templatenarrowcasting),	(Select max(id) from olvg_amsterdam .containerarea));