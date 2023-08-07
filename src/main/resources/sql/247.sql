select  updateAllSchemaWithGivenQuery('
--move train phase to train table
ALTER TABLE train ADD COLUMN trainPhase int;
--remove train phase from playlist table
ALTER TABLE playlist DROP COLUMN trainphase;
')
