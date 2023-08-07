--------------------- add fields for video requirements  ------------------------

select updateAllSchemaWithGivenQuery('
	ALTER TABLE publication ADD column videowidth integer;
	ALTER TABLE publication ADD column videoheight integer;
	ALTER TABLE publication ADD column videominrate integer;
	ALTER TABLE publication ADD column videomaxrate integer;

	ALTER TABLE broadcastwrapper ADD column active boolean;

	ALTER TABLE contentblock ADD column videoheight integer;
	ALTER TABLE contentblock ADD column videowidth integer;
	ALTER TABLE contentblock ADD column videominrate integer;
	ALTER TABLE contentblock ADD column videomaxrate integer;
');
