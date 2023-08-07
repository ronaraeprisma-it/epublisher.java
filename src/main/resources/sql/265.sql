select updateAllSchemaWithGivenQuery('
	Alter table geolocation ADD COLUMN externalname character varying,
	ADD COLUMN companyname character varying,
	ADD COLUMN website character varying,
	ADD COLUMN description character varying,
	ADD COLUMN businessarea character varying,
	ADD COLUMN street character varying,
	ADD COLUMN postalcode character varying,
	ADD COLUMN housenumber character varying,
	ADD COLUMN state character varying,
	ADD COLUMN openinghour integer,
	ADD COLUMN openingminute integer,
	ADD COLUMN closehour integer,
	ADD COLUMN closeminute integer,
	ADD COLUMN visitorsperweek integer,
	ADD COLUMN percentagemalevisitors integer,
	ADD COLUMN percentagefemalevisitors integer;

	Alter table publication ADD COLUMN geolocation_id integer;
');
