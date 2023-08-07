select  updateAllSchemaWithGivenQuery('

ALTER TABLE article ADD COLUMN "createdby" INTEGER;

ALTER TABLE article DROP COLUMN "createdbyname";

')