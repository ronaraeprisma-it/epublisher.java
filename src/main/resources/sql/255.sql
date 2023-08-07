--- Add permission to roles
select updateAllSchemaWithGivenQuery('
	INSERT INTO permission (id, entityversion, name, nameid) VALUES (nextval(''epublisher_sequence''), 0, ''Routes Tab'', ''routes-tab'');
');
