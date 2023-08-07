--Woord en Daad template 3: Video with logo and cover
INSERT INTO woord_en_daad.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('woord_en_daad.epublisher_sequence'),	1,	'woord_en_daad-image-video-fullscreen',	'Afbeelding en Video'	,null, null, null);

INSERT INTO woord_en_daad.containerarea (id, entityversion, containerposition) VALUES (nextval('woord_en_daad.epublisher_sequence'), 1, 1);
INSERT INTO woord_en_daad.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from woord_en_daad.containerarea),'video');
INSERT INTO woord_en_daad.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from woord_en_daad.templatenarrowcasting),	(Select max(id) from woord_en_daad.containerarea));