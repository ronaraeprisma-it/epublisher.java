
select updateAllSchemaWithGivenQuery('
ALTER TABLE
   publication_group ADD COLUMN mandatory BOOLEAN;

ALTER TABLE
    publication_group ALTER COLUMN mandatory SET DEFAULT false;
    
UPDATE publication_group   SET mandatory = false;');