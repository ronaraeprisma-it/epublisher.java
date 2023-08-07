select  updateAllSchemaWithGivenQuery('

-- train table
CREATE TABLE train(id INTEGER NOT NULL, previousstation character varying(255), nextstation character varying(255),
 finaldestination character varying(255), entityversion integer NOT NULL DEFAULT 1,
 PRIMARY KEY (id), UNIQUE (id));

GRANT ALL ON TABLE train TO epublisher;
GRANT SELECT, REFERENCES ON TABLE train TO dashboard;

-- train ride numbers table
CREATE TABLE train_rides(train_id INTEGER NOT NULL, trainrides character varying(255));
ALTER TABLE train_rides ADD CONSTRAINT fk_train_rides_train_id  FOREIGN KEY (train_id)  REFERENCES  train(id);

GRANT ALL ON TABLE train_rides TO epublisher;
GRANT SELECT, REFERENCES ON TABLE train_rides TO dashboard;

-- train equipment numbers table
CREATE TABLE train_equipments(train_id INTEGER NOT NULL, trainequipments character varying(255));
ALTER TABLE train_equipments ADD CONSTRAINT fk_train_equipments_train_id  FOREIGN KEY (train_id)  REFERENCES  train(id);

GRANT ALL ON TABLE train_equipments TO epublisher;
GRANT SELECT, REFERENCES ON TABLE train_equipments TO dashboard;

-- playlist train join table
CREATE TABLE playlist_train(playlist_id INTEGER NOT NULL, train_id INTEGER NOT NULL,
 PRIMARY KEY (playlist_id , train_id ), UNIQUE (playlist_id , train_id ));
ALTER TABLE playlist_train ADD CONSTRAINT fk_playlist_train_playlistid  FOREIGN KEY (playlist_id)  REFERENCES  playlist(id);
ALTER TABLE playlist_train ADD CONSTRAINT fk_playlist_train_trainid  FOREIGN KEY (train_id)  REFERENCES  train(id);

GRANT ALL ON TABLE playlist_train TO epublisher;
GRANT SELECT, REFERENCES ON TABLE playlist_train TO dashboard;

-- train phase
ALTER TABLE playtime ADD COLUMN trainphase character varying(255);
')
