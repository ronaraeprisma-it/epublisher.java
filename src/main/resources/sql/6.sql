-- TODO : incase this update has already run fix the tables in the next update script

-- broadcastwrapper
CREATE TABLE broadcastwrapper
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  alreadypublished boolean NOT NULL,
  orderofbroadcast integer,
  broadcast_id integer
);

ALTER TABLE broadcastwrapper
  ADD CONSTRAINT pk_broadcastwrapper
  PRIMARY KEY (id);

ALTER TABLE broadcastwrapper
  ADD CONSTRAINT fkee24cb2e908ae87
  FOREIGN KEY (broadcast_id)
  REFERENCES broadcast (id);

-- playlist
CREATE TABLE playlist
(
  id integer NOT NULL,
  publication_id integer,
  entityversion integer NOT NULL,
  deleted boolean NOT NULL,
  description varchar(255),
  lastupdated timestamp,
  name varchar(255) NOT NULL,
  priority integer NOT NULL,
  publicationdate timestamp,
  settingsdifferentthanpublished boolean NOT NULL
);

ALTER TABLE playlist
  ADD CONSTRAINT pk_playlist
  PRIMARY KEY (id);

ALTER TABLE playlist
  ADD CONSTRAINT fk73e0e5f285f7ba0d
  FOREIGN KEY (publication_id)
  REFERENCES publication (id);
  
-- playlist_broadcastwrapper
CREATE TABLE playlist_broadcastwrapper
(
  playlist_id integer NOT NULL,
  broadcastwrappers_id integer NOT NULL
);

ALTER TABLE playlist_broadcastwrapper
  ADD CONSTRAINT fk17392b5f9c54866d
  FOREIGN KEY (playlist_id)
  REFERENCES playlist (id);
ALTER TABLE playlist_broadcastwrapper
  ADD CONSTRAINT fk17392b5fc45239e
  FOREIGN KEY (broadcastwrappers_id)
  REFERENCES broadcastwrapper (id);

-- playtime
CREATE TABLE playtime
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  startdate timestamp without time zone,
  enddate timestamp without time zone,
  starthour integer,
  startminute integer,
  endhour integer,
  endminute integer,
  playlist_id integer
);

ALTER TABLE playtime
  ADD CONSTRAINT pk_playtime
  PRIMARY KEY (id);

-- playtime_days
CREATE TABLE playtime_days
(
  playtime_id integer NOT NULL,
  days integer
);

ALTER TABLE playtime_days
  ADD CONSTRAINT fk22dc2d954320182d
  FOREIGN KEY (playtime_id)
  REFERENCES playtime (id);
