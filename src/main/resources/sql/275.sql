select  updateAllSchemaWithGivenQuery('
CREATE TABLE epublisheruser_location (epublisheruser_id INTEGER NOT NULL, location_id INTEGER NOT NULL, PRIMARY KEY (epublisheruser_id, location_id));

ALTER TABLE epublisheruser_location 
	ADD CONSTRAINT epu_location_epu_epuid_fkey 
	FOREIGN KEY ("epublisheruser_id") 
	REFERENCES epublisheruser ("id");

ALTER TABLE epublisheruser_location 
	ADD CONSTRAINT epu_location_tn_atnid_fkey 
	FOREIGN KEY ("location_id") 
	REFERENCES location ("id");
');
