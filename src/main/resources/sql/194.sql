--Woord en Daad template 4: Tekst + foto horizontal
INSERT INTO woord_en_daad .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('woord_en_daad .epublisher_sequence'),	1,	'woord_en_daad-text-photo-horizontal',	'Text Photo Horizontal'	,null, null, '32:9');

INSERT INTO woord_en_daad .containerarea (id, entityversion, containerposition) VALUES (nextval('woord_en_daad .epublisher_sequence'), 1, 1);
INSERT INTO woord_en_daad .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from woord_en_daad .containerarea),'tekst');
INSERT INTO woord_en_daad .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from woord_en_daad .templatenarrowcasting),	(Select max(id) from woord_en_daad .containerarea));

INSERT INTO woord_en_daad .containerarea (id, entityversion, containerposition) VALUES (nextval('woord_en_daad .epublisher_sequence'), 1, 2);
INSERT INTO woord_en_daad .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from woord_en_daad .containerarea),'afbeelding');
INSERT INTO woord_en_daad .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from woord_en_daad .templatenarrowcasting),	(Select max(id) from woord_en_daad .containerarea));
