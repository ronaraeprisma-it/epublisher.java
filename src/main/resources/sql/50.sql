--INSERT INTO medi_h01.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('medi_h01.epublisher_sequence'),	1,	'medi-video-fullscreen',	'Video-Fullscreen'	,null);

--INSERT INTO medi_h01.containerarea (id, entityversion, containerposition) VALUES (nextval('medi_h01.epublisher_sequence'), 1, 1);




--INSERT INTO medi_h01.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from medi_h01.containerarea),'video');




--INSERT INTO medi_h01.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from medi_h01.templatenarrowcasting),	(Select max(id) from medi_h01.containerarea));

grant select, references,update on epublisheruser to dashboard;