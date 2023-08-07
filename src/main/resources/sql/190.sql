-- powerpoint template
select updateAllSchemaWithGivenQuery('
INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval(''epublisher_sequence''),	1,	''powerpoint-image'',	''Powerpoint''	,null);
INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''powerpoint'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));
');