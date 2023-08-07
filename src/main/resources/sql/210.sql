
select updateAllSchemaWithGivenQuery('
ALTER TABLE playlist
    ALTER COLUMN state SET DATA TYPE text USING state::text ,
    ALTER COLUMN lastsubmittedby SET DATA TYPE Text USING lastsubmittedby::text ,
    ALTER COLUMN lastmodifiedby SET DATA TYPE text USING lastmodifiedby::text;


');
