select  updateAllSchemaWithGivenQuery('
	ALTER TABLE playtime ADD COLUMN phase int;
	ALTER TABLE playtime ADD COLUMN repetition int;
	ALTER TABLE playtime ADD COLUMN interval int;
');
