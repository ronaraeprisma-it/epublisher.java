------------------------ WTC: locaties ---------------------------
select updatespecificschema('world_trade_center',
'
INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''epublisher_sequence''),	1,	''wtc-locaties'',	''WTC locaties''	,null, null, ''120:737'');

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''afbeelding'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));
'           
); 


------------------------ Central Park: Foto links + tekst AND Central Park: Foto rechts + tekst ---------------------------
select updatespecificschema('centralpark',
'
INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''epublisher_sequence''),	1,	''centralpark-fotolinks'',	''Foto links + tekst''	,null, null, ''1:1'');

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 2);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''afbeelding'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));


INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''epublisher_sequence''),	1,	''centralpark-fotorechtsandtekst'',	''Foto rechts + tekst''	,null, null, ''1:1'');

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 2);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 3);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''afbeelding'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));
'           
); 



------------------------ Fysio 058: Tekst AND Fysio 058: Tekst + foto rechts ---------------------------
select updatespecificschema('fysio_058',
'
INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''epublisher_sequence''),	1,	''fysio-tekst'',	''Tekst''	,null, null, ''1:1'');

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 2);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''epublisher_sequence''),	1,	''fysio-fotorechtsandtekst'',	''Tekst + foto rechts''	,null, null, ''1:1'');

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 2);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''afbeelding'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));
'           
); 

