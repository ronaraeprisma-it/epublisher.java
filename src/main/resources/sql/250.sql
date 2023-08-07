--add max playlists to publication
select  updateAllSchemaWithGivenQuery('
	ALTER TABLE publication ADD COLUMN maxplaylists int;
')
