DELETE
FROM ziekenhuis_martini.playlist_broadcastwrapper AS pt 
USING ziekenhuis_martini.templatenarrowcasting AS ptm, ziekenhuis_martini.broadcast as ptb, ziekenhuis_martini.broadcastwrapper as ptw
	WHERE ptm.id = ptb.template_id
	AND ptb.id = ptw.broadcast_id
	AND pt.broadcastwrappers_id = ptw.id
	AND ptm.htmltemplate = 'zm-landscape-slide';

DELETE
FROM ziekenhuis_martini.broadcastwrapper AS pt 
USING ziekenhuis_martini.templatenarrowcasting AS ptm, ziekenhuis_martini.broadcast as ptb
	WHERE ptm.id = ptb.template_id
	AND ptb.id = pt.broadcast_id
	AND ptm.htmltemplate = 'zm-landscape-slide';

DELETE
FROM ziekenhuis_martini.broadcast_contentblock AS pt 
USING ziekenhuis_martini.templatenarrowcasting AS ptm, ziekenhuis_martini.broadcast as ptb
	WHERE ptm.id = ptb.template_id
	AND ptb.id = pt.broadcast_id
	AND ptm.htmltemplate = 'zm-landscape-slide';

DELETE
FROM ziekenhuis_martini.broadcast AS pt 
USING ziekenhuis_martini.templatenarrowcasting AS ptm 
	WHERE ptm.id = pt.template_id
	AND ptm.htmltemplate = 'zm-landscape-slide';

DELETE
FROM ziekenhuis_martini.templatenarrowcasting_containerarea AS pt 
USING ziekenhuis_martini.templatenarrowcasting AS ptm 
	WHERE ptm.id = pt.templatenarrowcasting_id
	AND ptm.htmltemplate = 'zm-landscape-slide';

DELETE
FROM ziekenhuis_martini.epublisheruser_templatenarrowcasting AS ut 
USING ziekenhuis_martini.templatenarrowcasting AS ptm 
	WHERE ptm.id = ut.availabletemplates_id
	AND ptm.htmltemplate = 'zm-landscape-slide';

DELETE 
FROM ziekenhuis_martini.templatenarrowcasting
	WHERE htmltemplate = 'zm-landscape-slide';

UPDATE ziekenhuis_martini.templatenarrowcasting
	SET imageaspectratio='9:8'
	WHERE htmltemplate = 'zm-portrait-slide';
