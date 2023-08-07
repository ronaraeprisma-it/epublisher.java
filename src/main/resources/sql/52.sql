INSERT INTO medi_fysio.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('medi_fysio.epublisher_sequence'),	1,	'medi-video-fullscreen',	'Video-Fullscreen'	,null);

INSERT INTO medi_fysio.containerarea (id, entityversion, containerposition) VALUES (nextval('medi_fysio.epublisher_sequence'), 1, 1);
INSERT INTO medi_fysio.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from medi_fysio.containerarea),'video');
INSERT INTO medi_fysio.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from medi_fysio.templatenarrowcasting),	(Select max(id) from medi_fysio.containerarea));

