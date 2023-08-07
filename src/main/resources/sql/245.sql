-- add ad id on the broadcastwrapper level
select  updateAllSchemaWithGivenQuery('
ALTER TABLE broadcastwrapper ADD COLUMN advertisementid varchar;
')
