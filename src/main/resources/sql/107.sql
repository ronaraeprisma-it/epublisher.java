---------- Website Fullscreen Template -------------


INSERT INTO Bernhoven.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Bernhoven.epublisher_sequence'),	1,	'Bernhoven-website-fullscreen',	'website-fullscreen'	,null);

INSERT INTO Bernhoven.containerarea (id, entityversion, containerposition) VALUES (nextval('Bernhoven.epublisher_sequence'), 1, 1);
INSERT INTO Bernhoven.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Bernhoven.containerarea),'website');
INSERT INTO Bernhoven.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Bernhoven.templatenarrowcasting),	(Select max(id) from Bernhoven.containerarea));

------------------------------------------------------------------------

INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-website-fullscreen',	'website-fullscreen'	,null);

INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'website');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));

------------------------------------------------------------------------

INSERT INTO Youz.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Youz.epublisher_sequence'),	1,	'youz-website-fullscreen',	'website-fullscreen'	,null);

INSERT INTO Youz.containerarea (id, entityversion, containerposition) VALUES (nextval('Youz.epublisher_sequence'), 1, 1);
INSERT INTO Youz.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Youz.containerarea),'website');
INSERT INTO Youz.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Youz.templatenarrowcasting),	(Select max(id) from Youz.containerarea));

------------------------------------------------------------------------

INSERT INTO Ziekenhuis_Martini.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Ziekenhuis_Martini.epublisher_sequence'),	1,	'zm-website-fullscreen',	'website-fullscreen'	,null);

INSERT INTO Ziekenhuis_Martini.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini.epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Martini.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini.containerarea),'website');
INSERT INTO Ziekenhuis_Martini.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini.containerarea));


---------- Fullscreen Image Template-------------

INSERT INTO Bernhoven.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Bernhoven.epublisher_sequence'),	1,	'Bernhoven-fullscreen-Image',	'Full Screen Image'	,null);


INSERT INTO Bernhoven.containerarea (id, entityversion, containerposition) VALUES (nextval('Bernhoven.epublisher_sequence'), 1, 1);
INSERT INTO Bernhoven.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Bernhoven.containerarea),'afbeelding');
INSERT INTO Bernhoven.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Bernhoven.templatenarrowcasting),	(Select max(id) from Bernhoven.containerarea));

------------------------------------------------------------------------

INSERT INTO Spaarne_Gasthuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Spaarne_Gasthuis.epublisher_sequence'),	1,	'sg-fullscreen-Image',	'Full Screen Image'	,null);


INSERT INTO Spaarne_Gasthuis.containerarea (id, entityversion, containerposition) VALUES (nextval('Spaarne_Gasthuis.epublisher_sequence'), 1, 1);
INSERT INTO Spaarne_Gasthuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Spaarne_Gasthuis.containerarea),'afbeelding');
INSERT INTO Spaarne_Gasthuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Spaarne_Gasthuis.templatenarrowcasting),	(Select max(id) from Spaarne_Gasthuis.containerarea));

------------------------------------------------------------------------

INSERT INTO Youz.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Youz.epublisher_sequence'),	1,	'youz-fullscreen-Image',	'Full Screen Image'	,null);


INSERT INTO Youz.containerarea (id, entityversion, containerposition) VALUES (nextval('Youz.epublisher_sequence'), 1, 1);
INSERT INTO Youz.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Youz.containerarea),'afbeelding');
INSERT INTO Youz.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Youz.templatenarrowcasting),	(Select max(id) from Youz.containerarea));

------------------------------------------------------------------------

INSERT INTO Ziekenhuis_Martini.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Ziekenhuis_Martini.epublisher_sequence'),	1,	'zm-fullscreen-Image',	'Full Screen Image'	,null);


INSERT INTO Ziekenhuis_Martini.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Martini.epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Martini.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Martini.containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Martini.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Martini.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Martini.containerarea));



------New Bernhoven Template---------
INSERT INTO Bernhoven .templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id) VALUES ( nextval('Bernhoven .epublisher_sequence'),	1,	'Bernhoven-employee-slide',	'Employee slide'	,null);


INSERT INTO Bernhoven .containerarea (id, entityversion, containerposition) VALUES (nextval('Bernhoven .epublisher_sequence'), 1, 1);
INSERT INTO Bernhoven .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Bernhoven .containerarea),'tekst');
INSERT INTO Bernhoven .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Bernhoven .templatenarrowcasting),	(Select max(id) from Bernhoven .containerarea));


INSERT INTO Bernhoven .containerarea (id, entityversion, containerposition) VALUES (nextval('Bernhoven .epublisher_sequence'), 1, 2);
INSERT INTO Bernhoven .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Bernhoven .containerarea),'afbeelding');
INSERT INTO Bernhoven .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Bernhoven .templatenarrowcasting),	(Select max(id) from Bernhoven .containerarea));