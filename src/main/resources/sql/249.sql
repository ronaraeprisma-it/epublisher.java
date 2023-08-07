-- screen geolocation join table
select updateAllSchemaWithGivenQuery('
	CREATE TABLE screen_geolocation(screen_id INTEGER NOT NULL, geolocation_id INTEGER NOT NULL,
	 PRIMARY KEY (screen_id , geolocation_id ), UNIQUE (screen_id , geolocation_id ));
	ALTER TABLE screen_geolocation ADD CONSTRAINT fk_screen_geolocation_screenid  FOREIGN KEY (screen_id)  REFERENCES  screen(id);
	ALTER TABLE screen_geolocation ADD CONSTRAINT fk_screen_geolocation_geolocationid  FOREIGN KEY (geolocation_id)  REFERENCES  geolocation(id);
	
	GRANT ALL ON TABLE screen_geolocation TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE screen_geolocation TO dashboard;
');
