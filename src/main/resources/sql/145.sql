select  updateAllSchemaWithGivenQuery('
CREATE TABLE epublisheruser_app (epublisheruser_id INTEGER NOT NULL, availableapps_id VARCHAR NOT NULL, PRIMARY KEY (epublisheruser_id, availableapps_id));

ALTER TABLE epublisheruser_app 
	ADD CONSTRAINT epu_tn_epu_epuid_fkey 
	FOREIGN KEY ("epublisheruser_id") 
	REFERENCES epublisheruser ("id");

ALTER TABLE epublisheruser_app 
	ADD CONSTRAINT epu_tn_tn_atnid_fkey 
	FOREIGN KEY ("availableapps_id") 
	REFERENCES usertemplateapps ("id");
');