select  updateAllSchemaWithGivenQuery('
-- train repetition
ALTER TABLE playlist ADD COLUMN trainrepetition int;
-- train interval
ALTER TABLE playlist ADD COLUMN traininterval int;
-- train phase
ALTER TABLE playlist ADD COLUMN trainPhase int;
')
