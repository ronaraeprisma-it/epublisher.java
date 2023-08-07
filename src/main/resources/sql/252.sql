select  updateAllSchemaWithGivenQuery('
	ALTER TABLE route ADD COLUMN routeicon character varying;

	ALTER TABLE templatenarrowcasting
		ADD COLUMN wayfinder_icon_support boolean default false;
');

