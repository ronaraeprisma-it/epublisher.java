select  updateAllSchemaWithGivenQuery('
-- playlist screengroup join table
CREATE TABLE playlist_screengroup(playlist_id INTEGER NOT NULL, screengroup_id INTEGER NOT NULL,
 PRIMARY KEY (playlist_id , screengroup_id ), UNIQUE (playlist_id , screengroup_id ));
ALTER TABLE playlist_screengroup ADD CONSTRAINT fk_playlist_screengroup_playlistid  FOREIGN KEY (playlist_id)  REFERENCES  playlist(id);
ALTER TABLE playlist_screengroup ADD CONSTRAINT fk_playlist_screengroup_screengroupid  FOREIGN KEY (screengroup_id)  REFERENCES  screengroup(id);

GRANT ALL ON TABLE playlist_screengroup TO epublisher;
GRANT SELECT, REFERENCES ON TABLE playlist_screengroup TO dashboard;
')