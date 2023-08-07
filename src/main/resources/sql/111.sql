/*Add missing default templates (fullscreen-image and fullscreen-website) */

/*olvg_amsterdam Fullscreen Website Template*/ 
INSERT INTO olvg_amsterdam.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('olvg_amsterdam.epublisher_sequence'),	1,	'olvg-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO olvg_amsterdam.containerarea (id, entityversion, containerposition) VALUES (nextval('olvg_amsterdam.epublisher_sequence'), 1, 1);
INSERT INTO olvg_amsterdam.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from olvg_amsterdam.containerarea),'website');
INSERT INTO olvg_amsterdam.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from olvg_amsterdam.templatenarrowcasting),	(Select max(id) from olvg_amsterdam.containerarea));


/*olvg_amsterdam Fullscreen Image Template*/ 
INSERT INTO olvg_amsterdam.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('olvg_amsterdam.epublisher_sequence'),	1,	'olvg-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO olvg_amsterdam.containerarea (id, entityversion, containerposition) VALUES (nextval('olvg_amsterdam.epublisher_sequence'), 1, 1);
INSERT INTO olvg_amsterdam.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from olvg_amsterdam.containerarea),'afbeelding');
INSERT INTO olvg_amsterdam.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from olvg_amsterdam.templatenarrowcasting),	(Select max(id) from olvg_amsterdam.containerarea));


/*Viecuri Fullscreen Website Template*/ 
INSERT INTO Viecuri.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Viecuri.epublisher_sequence'),	1,	'viecuri-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO Viecuri.containerarea (id, entityversion, containerposition) VALUES (nextval('Viecuri.epublisher_sequence'), 1, 1);
INSERT INTO Viecuri.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Viecuri.containerarea),'website');
INSERT INTO Viecuri.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Viecuri.templatenarrowcasting),	(Select max(id) from Viecuri.containerarea));


/*Viecuri Fullscreen Image Template*/ 
INSERT INTO Viecuri.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Viecuri.epublisher_sequence'),	1,	'viecuri-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO Viecuri.containerarea (id, entityversion, containerposition) VALUES (nextval('Viecuri.epublisher_sequence'), 1, 1);
INSERT INTO Viecuri.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Viecuri.containerarea),'afbeelding');
INSERT INTO Viecuri.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Viecuri.templatenarrowcasting),	(Select max(id) from Viecuri.containerarea));


/*Ziekenhuis Rivierenland Fullscreen Website Template*/ 
INSERT INTO Ziekenhuis_Rivierenland.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Ziekenhuis_Rivierenland.epublisher_sequence'),	1,	'zr-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO Ziekenhuis_Rivierenland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Rivierenland.epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Rivierenland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Rivierenland.containerarea),'website');
INSERT INTO Ziekenhuis_Rivierenland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Rivierenland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Rivierenland.containerarea));


/*Ziekenhuis_Rivierenland Fullscreen Image Template*/ 
INSERT INTO Ziekenhuis_Rivierenland.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Ziekenhuis_Rivierenland.epublisher_sequence'),	1,	'zr-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO Ziekenhuis_Rivierenland.containerarea (id, entityversion, containerposition) VALUES (nextval('Ziekenhuis_Rivierenland.epublisher_sequence'), 1, 1);
INSERT INTO Ziekenhuis_Rivierenland.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Ziekenhuis_Rivierenland.containerarea),'afbeelding');
INSERT INTO Ziekenhuis_Rivierenland.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Ziekenhuis_Rivierenland.templatenarrowcasting),	(Select max(id) from Ziekenhuis_Rivierenland.containerarea));


/*Medi-Fysio Fullscreen Image Template*/ 
INSERT INTO medi_fysio.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('medi_fysio.epublisher_sequence'),	1,	'medi-fysio-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO medi_fysio.containerarea (id, entityversion, containerposition) VALUES (nextval('medi_fysio.epublisher_sequence'), 1, 1);
INSERT INTO medi_fysio.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from medi_fysio.containerarea),'afbeelding');
INSERT INTO medi_fysio.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from medi_fysio.templatenarrowcasting),	(Select max(id) from medi_fysio.containerarea));


/*AZ Fullscreen Website Template*/ 
INSERT INTO alrijne_ziekenhuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('alrijne_ziekenhuis.epublisher_sequence'),	1,	'az-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO alrijne_ziekenhuis.containerarea (id, entityversion, containerposition) VALUES (nextval('alrijne_ziekenhuis.epublisher_sequence'), 1, 1);
INSERT INTO alrijne_ziekenhuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from alrijne_ziekenhuis.containerarea),'website');
INSERT INTO alrijne_ziekenhuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from alrijne_ziekenhuis.templatenarrowcasting),	(Select max(id) from alrijne_ziekenhuis.containerarea));


/*AZ Fullscreen Image Template*/ 
INSERT INTO alrijne_ziekenhuis.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('alrijne_ziekenhuis.epublisher_sequence'),	1,	'az-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO alrijne_ziekenhuis.containerarea (id, entityversion, containerposition) VALUES (nextval('alrijne_ziekenhuis.epublisher_sequence'), 1, 1);
INSERT INTO alrijne_ziekenhuis.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from alrijne_ziekenhuis.containerarea),'afbeelding');
INSERT INTO alrijne_ziekenhuis.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from alrijne_ziekenhuis.templatenarrowcasting),	(Select max(id) from alrijne_ziekenhuis.containerarea));


/* Fysio_Kamp_Lolkema Fullscreen Website Template */
INSERT INTO Fysio_Kamp_Lolkema.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Fysio_Kamp_Lolkema.epublisher_sequence'),	1,	'fkl-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO Fysio_Kamp_Lolkema.containerarea (id, entityversion, containerposition) VALUES (nextval('Fysio_Kamp_Lolkema.epublisher_sequence'), 1, 1);
INSERT INTO Fysio_Kamp_Lolkema.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Fysio_Kamp_Lolkema.containerarea),'website');
INSERT INTO Fysio_Kamp_Lolkema.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Fysio_Kamp_Lolkema.templatenarrowcasting),	(Select max(id) from Fysio_Kamp_Lolkema.containerarea));


/* Fysio_Kamp_Lolkema Fullscreen Image Template */
INSERT INTO Fysio_Kamp_Lolkema.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('Fysio_Kamp_Lolkema.epublisher_sequence'),	1,	'fkl-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO Fysio_Kamp_Lolkema.containerarea (id, entityversion, containerposition) VALUES (nextval('Fysio_Kamp_Lolkema.epublisher_sequence'), 1, 1);
INSERT INTO Fysio_Kamp_Lolkema.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from Fysio_Kamp_Lolkema.containerarea),'afbeelding');
INSERT INTO Fysio_Kamp_Lolkema.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from Fysio_Kamp_Lolkema.templatenarrowcasting),	(Select max(id) from Fysio_Kamp_Lolkema.containerarea));


/* TJS Fullscreen Website Template */
INSERT INTO ziekenhuis_tjongerschans.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('ziekenhuis_tjongerschans.epublisher_sequence'),	1,	'tjs-website-fullscreen',	'Website Fullscreen'	,null, null);

INSERT INTO ziekenhuis_tjongerschans.containerarea (id, entityversion, containerposition) VALUES (nextval('ziekenhuis_tjongerschans.epublisher_sequence'), 1, 1);
INSERT INTO ziekenhuis_tjongerschans.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ziekenhuis_tjongerschans.containerarea),'website');
INSERT INTO ziekenhuis_tjongerschans.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ziekenhuis_tjongerschans.templatenarrowcasting),	(Select max(id) from ziekenhuis_tjongerschans.containerarea));


/* TJS Fullscreen Image Template */
INSERT INTO ziekenhuis_tjongerschans.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id) VALUES ( nextval('ziekenhuis_tjongerschans.epublisher_sequence'),	1,	'tjs-fullscreen-Image',	'Full Screen Image'	,null, null);

INSERT INTO ziekenhuis_tjongerschans.containerarea (id, entityversion, containerposition) VALUES (nextval('ziekenhuis_tjongerschans.epublisher_sequence'), 1, 1);
INSERT INTO ziekenhuis_tjongerschans.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from ziekenhuis_tjongerschans.containerarea),'afbeelding');
INSERT INTO ziekenhuis_tjongerschans.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from ziekenhuis_tjongerschans.templatenarrowcasting),	(Select max(id) from ziekenhuis_tjongerschans.containerarea));

