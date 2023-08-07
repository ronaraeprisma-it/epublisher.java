select  updateAllSchemaWithGivenQuery('
-- geolocation table
CREATE TABLE geolocation(id INTEGER NOT NULL, latitude FLOAT, longitude FLOAT,
 range FLOAT, city character varying(255), entityversion integer NOT NULL DEFAULT 1,
 PRIMARY KEY (id), UNIQUE (id));

GRANT ALL ON TABLE geolocation TO epublisher;
GRANT SELECT, REFERENCES ON TABLE geolocation TO dashboard;

-- playlist geolocation join table
CREATE TABLE playlist_geolocation(playlist_id INTEGER NOT NULL, geolocation_id INTEGER NOT NULL,
 PRIMARY KEY (playlist_id , geolocation_id ), UNIQUE (playlist_id , geolocation_id ));
ALTER TABLE playlist_geolocation ADD CONSTRAINT fk_playlist_geolocation_playlistid  FOREIGN KEY (playlist_id)  REFERENCES  playlist(id);
ALTER TABLE playlist_geolocation ADD CONSTRAINT fk_playlist_geolocation_geolocationid  FOREIGN KEY (geolocation_id)  REFERENCES  geolocation(id);

GRANT ALL ON TABLE playlist_geolocation TO epublisher;
GRANT SELECT, REFERENCES ON TABLE playlist_geolocation TO dashboard;
')
