select updatespecificschema('dorcas',
'
INSERT INTO dorcas.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''dorcas.epublisher_sequence''),	1,	''dorcas-actie'',	''Actie''	,null, null, ''32 : 27'');

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 1);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 2);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 3);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''afbeelding'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));



INSERT INTO dorcas.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''dorcas.epublisher_sequence''),	1,	''dorcas-tekst-afbeeldingen'',	''Tekst & Afbeeldingen''	,null, null, ''32 : 27'');

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 1);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 2);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 3);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 4);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''afbeelding'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 5);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''afbeelding'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));




INSERT INTO dorcas.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''dorcas.epublisher_sequence''),	1,	''dorcas-full-screen-text'',	''Full Screen Tekst''	,null, null, ''32 : 27'');

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 1);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 2);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 3);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));

INSERT INTO dorcas.containerarea (id, entityversion, containerposition) VALUES (nextval(''dorcas.epublisher_sequence''), 1, 4);
INSERT INTO dorcas.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from dorcas.containerarea),''tekst'');
INSERT INTO dorcas.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from dorcas.templatenarrowcasting),	(Select max(id) from dorcas.containerarea));




'           
);
