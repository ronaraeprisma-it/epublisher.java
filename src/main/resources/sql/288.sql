------------------------ WTC Art Gallery Text Template ---------------------------

select updatespecificschema('world_trade_center',
'
INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval(''epublisher_sequence''),	1,	''wtc-art-gallery-text'',	''Art Gallery Tekst''	,null, null, null);

INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''tekst'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea));

'           
);
