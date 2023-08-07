select  updateAllSchemaWithGivenQuery('
-- train repetition
ALTER TABLE playtime ADD COLUMN trainrepetition int;
-- train interval
ALTER TABLE playtime ADD COLUMN traininterval int;
')