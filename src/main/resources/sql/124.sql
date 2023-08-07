----------------------- Zaan_Apotheek Video-Split-Screen -----------------

INSERT INTO Zaan_Apotheek .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Zaan_Apotheek .epublisher_sequence'),	1,	'zaan-video-split-screen',	'Video-Split-Screen'	,null ,null);

INSERT INTO Zaan_Apotheek .containerarea (id, entityversion, containerposition) VALUES (nextval('Zaan_Apotheek .epublisher_sequence'), 1, 1);
INSERT INTO Zaan_Apotheek .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Zaan_Apotheek .containerarea),'video');
INSERT INTO Zaan_Apotheek .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Zaan_Apotheek .templatenarrowcasting),	(Select max(id) from Zaan_Apotheek .containerarea));

------------------------ Albert_Sweitzer Full Text slide ---------------------------

INSERT INTO Albert_Sweitzer .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Albert_Sweitzer .epublisher_sequence'),	1,	'asz-full-text-slide',	'Full Text Slide'	,null, null);


INSERT INTO Albert_Sweitzer .containerarea (id, entityversion, containerposition) VALUES (nextval('Albert_Sweitzer .epublisher_sequence'), 1, 1);
INSERT INTO Albert_Sweitzer .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Albert_Sweitzer .containerarea),'tekst');
INSERT INTO Albert_Sweitzer .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Albert_Sweitzer .templatenarrowcasting),	(Select max(id) from Albert_Sweitzer .containerarea));


------------------------ Albert_Sweitzer Text + Image slide ---------------------------

INSERT INTO Albert_Sweitzer .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('Albert_Sweitzer .epublisher_sequence'),	1,	'asz-text-image-slide',	'Text + Image slide'	,null, null, '10:11');


INSERT INTO Albert_Sweitzer .containerarea (id, entityversion, containerposition) VALUES (nextval('Albert_Sweitzer .epublisher_sequence'), 1, 1);
INSERT INTO Albert_Sweitzer .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Albert_Sweitzer .containerarea),'tekst');
INSERT INTO Albert_Sweitzer .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Albert_Sweitzer .templatenarrowcasting),	(Select max(id) from Albert_Sweitzer .containerarea));


INSERT INTO Albert_Sweitzer .containerarea (id, entityversion, containerposition) VALUES (nextval('Albert_Sweitzer .epublisher_sequence'), 1, 2);
INSERT INTO Albert_Sweitzer .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Albert_Sweitzer .containerarea),'afbeelding');
INSERT INTO Albert_Sweitzer .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Albert_Sweitzer .templatenarrowcasting),	(Select max(id) from Albert_Sweitzer .containerarea));
