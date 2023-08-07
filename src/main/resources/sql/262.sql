--- Add title check/boolean for wayfinder templates
select updateAllSchemaWithGivenQuery('
	ALTER TABLE templatenarrowcasting 
	ADD COLUMN enableTitle boolean default false;
');
