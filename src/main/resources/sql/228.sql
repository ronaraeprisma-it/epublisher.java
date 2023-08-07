select updateAllSchemaWithGivenQuery('
	ALTER TABLE broadcastwrapper ADD column dtype varchar(31) DEFAULT ''BroadcastNarrowcastingNS'';
	ALTER TABLE playlist ADD column dtype varchar(31) DEFAULT ''PlaylistNarrowcastingNS'';

	INSERT INTO outputchannel (dtype, id, name, entityversion) VALUES (''OutputChannelNSTreinen'',12,''nstreinen'', 1);
');
