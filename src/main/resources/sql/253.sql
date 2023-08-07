select updatespecificschema('world_trade_center',
'
INSERT INTO templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio, wayfinder_icon_support) VALUES ( nextval(''epublisher_sequence''),	1,	''wtc-wayfinder-rotterdam-lobby'',	''Wayfinder Rotterdam Lobby''	,null, null, null, true);
INSERT INTO containerarea (id, entityversion, containerposition) VALUES (nextval(''epublisher_sequence''), 1, 1);
INSERT INTO containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from containerarea),''wayfinder'');
INSERT INTO templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from templatenarrowcasting),	(Select max(id) from containerarea)); 
'           
); 