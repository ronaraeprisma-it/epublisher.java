--- Add permission to roles
select updateAllSchemaWithGivenQuery('
	Alter table epublisheruser add maxplaylistpriority integer;
');
