--remove these columns, they are moved to playlist table
select  updateAllSchemaWithGivenQuery('
--remove train repetition
ALTER TABLE playtime DROP COLUMN trainrepetition;
--remove train interval
ALTER TABLE playtime DROP COLUMN traininterval;
--remove train phase
ALTER TABLE playtime DROP COLUMN trainphase;
')
