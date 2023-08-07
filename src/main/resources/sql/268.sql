-- Add max playlist duration to publication
select  updateAllSchemaWithGivenQuery('
	ALTER TABLE publication
	ADD COLUMN maxplaylistduration int;
')
