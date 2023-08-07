------------------------ WTC Lobby Screen Template ---------------------------
INSERT INTO World_Trade_Center.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('World_Trade_Center.epublisher_sequence'),	1,	'wtc-lobbyscherm',	'Lobby Screen'	,null, null, null);

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 1);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'video');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 2);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'website');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 3);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'website');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 4);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'website');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));

INSERT INTO World_Trade_Center.containerarea (id, entityversion, containerposition) VALUES (nextval('World_Trade_Center.epublisher_sequence'), 1, 5);
INSERT INTO World_Trade_Center.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from World_Trade_Center.containerarea),'website');
INSERT INTO World_Trade_Center.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from World_Trade_Center.templatenarrowcasting),	(Select max(id) from World_Trade_Center.containerarea));


