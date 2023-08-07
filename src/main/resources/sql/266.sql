select updateAllSchemaWithGivenQuery('
	Alter table epublisheruser ADD COLUMN lastlogindatetime timestamp without time zone;	
');
