INSERT INTO prisma_mcp.templatenarrowcasting (id, entityversion, htmltemplate, name, previewimage_id, defaultimage_id, imageaspectratio) VALUES ( nextval('prisma_mcp.epublisher_sequence'),	1,	'video-fullscreen-text',	'Video-Fullscreen-Text'	,null, null, null);

INSERT INTO prisma_mcp.containerarea (id, entityversion, containerposition) VALUES (nextval('prisma_mcp.epublisher_sequence'), 1, 1);
INSERT INTO prisma_mcp.containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from prisma_mcp.containerarea),'video');
INSERT INTO prisma_mcp.templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from prisma_mcp.templatenarrowcasting),	(Select max(id) from prisma_mcp.containerarea));

INSERT INTO prisma_mcp .containerarea (id, entityversion, containerposition) VALUES (nextval('prisma_mcp .epublisher_sequence'), 1, 2);
INSERT INTO prisma_mcp .containerarea_allowedcontent (containerarea_id, allowedcontent) VALUES ((Select max(id) from prisma_mcp .containerarea),'tekst');
INSERT INTO prisma_mcp .templatenarrowcasting_containerarea (templatenarrowcasting_id, containerareas_id) VALUES ((Select max(id) from prisma_mcp .templatenarrowcasting),	(Select max(id) from prisma_mcp .containerarea));