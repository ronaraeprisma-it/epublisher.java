--this file is here to compensate for a wrong SQL number which ran in PRD

--select  updateAllSchemaWithGivenQuery('
--	ALTER TABLE playtime ADD COLUMN phase int;
--	ALTER TABLE playtime ADD COLUMN repetition int;
--	ALTER TABLE playtime ADD COLUMN interval int;
--');
select updatespecificschema('prisma_mcp',''); 

