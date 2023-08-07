--add broadcast duration to publication
select  updateAllSchemaWithGivenQuery('
	ALTER TABLE publication ADD COLUMN broadcastduration int;
')
