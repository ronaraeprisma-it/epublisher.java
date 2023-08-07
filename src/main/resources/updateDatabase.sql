-- add templates to database
declare @templateid;
declare @containerid;

-- traditioneel template
SET @templateid = values(nextval('epublisher_sequence'));
SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO templatenarrowcasting(id, entityversion, htmltemplate, "name")
    VALUES (@templateid, 1, 'traditioneel', 'traditioneel');

INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 1);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 2);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 3);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 4);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 5);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 6);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'klok');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 7);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'twitter feed');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);


-- full screen template
SET @templateid = values(nextval('epublisher_sequence'));
SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO templatenarrowcasting(id, entityversion, htmltemplate, "name")
    VALUES (@templateid, 1, 'fullscreen', 'full screen');

INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 1);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 2);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'klok');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 3);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'RSS');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

 -- 2 kolom rechts template
SET @templateid = values(nextval('epublisher_sequence'));
SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO templatenarrowcasting(id, entityversion, htmltemplate, "name")
    VALUES (@templateid, 1, '2kolomrechts', '2 kolom rechts');

INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 1);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'klok');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 2);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'twitter feed');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 3);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 4);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'RSS');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

 -- full screen - crisis template
SET @templateid = values(nextval('epublisher_sequence'));
SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO templatenarrowcasting(id, entityversion, htmltemplate, "name")
    VALUES (@templateid, 1, 'fullscreencrisis', 'full screen - crisis');
    
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 1);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'tekst');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'video');
INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'afbeelding');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);

SET @containerid = values(nextval('epublisher_sequence'));
INSERT INTO containerarea(id, entityversion, containerposition)
    VALUES (@containerid, 1, 2);

INSERT INTO containerarea_allowedcontent(containerarea_id, allowedcontent)
    VALUES (@containerid, 'klok');

INSERT INTO templatenarrowcasting_containerarea(templatenarrowcasting_id, containerareas_id)
    VALUES (@templateid, @containerid);
