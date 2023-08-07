--- Add permission to roles
select updateAllSchemaWithGivenQuery('
	update permission set name = ''Wayfinder Routes'' where nameid = ''routes-tab'';
');
