Delete from ziekenhuis_tjongerschans.templatenarrowcasting_containerarea 
where templatenarrowcasting_id = (select id from ziekenhuis_tjongerschans.templatenarrowcasting where  htmltemplate = 'tjs-portrait-slide' ) and containerareas_id = (select containerarea_id from ziekenhuis_tjongerschans.containerarea_allowedcontent ac
 inner join ziekenhuis_tjongerschans.templatenarrowcasting_containerarea tc 
on ac.containerarea_id = tc.containerareas_id where tc.templatenarrowcasting_id = (select id from ziekenhuis_tjongerschans.templatenarrowcasting where  htmltemplate = 'tjs-portrait-slide') and ac.allowedcontent = 'afbeelding');

Delete from ziekenhuis_tjongerschans.templatenarrowcasting_containerarea 
where templatenarrowcasting_id = (select id from ziekenhuis_tjongerschans.templatenarrowcasting where  htmltemplate = 'tjs-portrait-slide' ) and containerareas_id = (select containerarea_id from ziekenhuis_tjongerschans.containerarea_allowedcontent ac
 inner join ziekenhuis_tjongerschans.templatenarrowcasting_containerarea tc 
on ac.containerarea_id = tc.containerareas_id where tc.templatenarrowcasting_id = (select id from ziekenhuis_tjongerschans.templatenarrowcasting where  htmltemplate = 'tjs-portrait-slide'));

Delete from ziekenhuis_tjongerschans.templatenarrowcasting_containerarea where templatenarrowcasting_id = (select id from ziekenhuis_tjongerschans.templatenarrowcasting 
where  htmltemplate = 'tjs-photoslide' ) and containerareas_id = (select containerarea_id from ziekenhuis_tjongerschans.containerarea_allowedcontent ac
 inner join ziekenhuis_tjongerschans.templatenarrowcasting_containerarea tc 
on ac.containerarea_id = tc.containerareas_id where tc.templatenarrowcasting_id = (select id from ziekenhuis_tjongerschans.templatenarrowcasting where  htmltemplate = 'tjs-photoslide'));

DELETE FROM ziekenhuis_tjongerschans.templatenarrowcasting
	WHERE htmltemplate = 'tjs-portrait-slide';
DELETE FROM ziekenhuis_tjongerschans.templatenarrowcasting
	WHERE htmltemplate = 'tjs-photoslide';