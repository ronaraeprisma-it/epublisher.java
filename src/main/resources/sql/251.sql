select updatespecificschema('zorgcentrale_noord',
'
INSERT INTO zorgcentrale_noord.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''zorgcentrale_noord.epublisher_sequence''),	1,	''zorgcentrale_noord-landscape-photo-rechts'',	''Landscape Photo Rechts''	,null, null, ''32 : 27'');

INSERT INTO zorgcentrale_noord.containerarea (id, entityversion, containerposition) VALUES (nextval(''zorgcentrale_noord.epublisher_sequence''), 1, 1);
INSERT INTO zorgcentrale_noord.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord.containerarea),''tekst'');
INSERT INTO zorgcentrale_noord.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord.templatenarrowcasting),	(Select max(id) from zorgcentrale_noord.containerarea));

INSERT INTO zorgcentrale_noord.containerarea (id, entityversion, containerposition) VALUES (nextval(''zorgcentrale_noord.epublisher_sequence''), 1, 2);
INSERT INTO zorgcentrale_noord.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord.containerarea),''tekst'');
INSERT INTO zorgcentrale_noord.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord.templatenarrowcasting),	(Select max(id) from zorgcentrale_noord.containerarea));

INSERT INTO zorgcentrale_noord.containerarea (id, entityversion, containerposition) VALUES (nextval(''zorgcentrale_noord.epublisher_sequence''), 1, 3);
INSERT INTO zorgcentrale_noord.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from zorgcentrale_noord.containerarea),''afbeelding'');
INSERT INTO zorgcentrale_noord.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from zorgcentrale_noord.templatenarrowcasting),	(Select max(id) from zorgcentrale_noord.containerarea));

'           
);
