select updateAllSchemaWithGivenQuery('
	Alter table broadcast ADD COLUMN deleted boolean default false;
');