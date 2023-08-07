------------------------ Split Screen Image Template ---------------------------
INSERT INTO Zaan_Apotheek.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Zaan_Apotheek.epublisher_sequence'),	1,	'zaan-image-split-screen',	'Image-Split-Screen'	,null, null,'16:9');


INSERT INTO Zaan_Apotheek.containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek.epublisher_sequence'), 1, 1);
INSERT INTO Zaan_Apotheek.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek.containerarea),'afbeelding');
INSERT INTO Zaan_Apotheek.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek.templatenarrowcasting),	(Select max(id) from Zaan_Apotheek.containerarea));