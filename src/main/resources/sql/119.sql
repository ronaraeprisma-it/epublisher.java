select  updateAllSchemaWithGivenQuery('
ALTER TABLE epublisheruser_articletype
ADD COLUMN read boolean NOT NULL DEFAULT false,
ADD COLUMN write boolean NOT NULL DEFAULT false;

ALTER TABLE epublisheruser_articletype
RENAME COLUMN availablearticletypes_id TO articletype_id;
'); 