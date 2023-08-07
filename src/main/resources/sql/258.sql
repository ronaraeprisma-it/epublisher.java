 --------- WEBSITE-FOOTER + WEBSITE-RSS TEMPLATE REMOVAL --------- 
select updatespecificschema('prisma_mcp',
'
 UPDATE broadcast AS b 
	SET template_id = null
	FROM templatenarrowcasting as t
	WHERE b.template_id = t.id
	AND t.htmltemplate = ''website-footer'';

DELETE
	FROM epublisheruser_templatenarrowcasting as u
	USING templatenarrowcasting as t 
	where t.htmltemplate = ''website-footer''
	and u.availabletemplates_id = t.id;
	
DELETE
	FROM templatenarrowcasting_containerarea as tc
	USING templatenarrowcasting as t 
	where t.htmltemplate = ''website-footer''
	and tc.templatenarrowcasting_id = t.id;
	
DELETE 
	FROM templatenarrowcasting
	WHERE htmltemplate = ''website-footer'';

UPDATE broadcast AS b 
	SET template_id = null
	FROM templatenarrowcasting as t
	WHERE b.template_id = t.id
	AND t.htmltemplate = ''website-rss'';

DELETE
	FROM epublisheruser_templatenarrowcasting as u
	USING templatenarrowcasting as t 
	where t.htmltemplate = ''website-rss''
	and u.availabletemplates_id = t.id;
	
DELETE
	FROM templatenarrowcasting_containerarea as tc
	USING templatenarrowcasting as t 
	where t.htmltemplate = ''website-rss''
	and tc.templatenarrowcasting_id = t.id;
	
DELETE 
	FROM templatenarrowcasting
	WHERE htmltemplate = ''website-rss'';

'  
); 
