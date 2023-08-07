select  updateAllSchemaWithGivenQuery('

ALTER TABLE articletype
ADD COLUMN deleted boolean NOT NULL DEFAULT false;

ALTER TABLE article
DROP COLUMN type;

')