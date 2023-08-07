INSERT INTO ProRail.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('ProRail.epublisher_sequence'),	1,	'prail-text-and-image',	'Text + Image'	,null);

INSERT INTO ProRail.containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail.epublisher_sequence'), 1, 1);
INSERT INTO ProRail.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail.containerarea),'tekst');
INSERT INTO ProRail.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail.templatenarrowcasting),	(Select max(id) from ProRail.containerarea));

INSERT INTO ProRail.containerarea (id, entityversion, containerposition) VALUES (nextval('ProRail.epublisher_sequence'), 1, 2);
INSERT INTO ProRail.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ProRail.containerarea),'afbeelding');
INSERT INTO ProRail.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ProRail.templatenarrowcasting),	(Select max(id) from ProRail.containerarea));