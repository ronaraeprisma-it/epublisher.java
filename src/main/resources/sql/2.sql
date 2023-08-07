-- add table screengroup
CREATE TABLE screengroup
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  description varchar(255),
  name varchar(255),
  screengroup_id integer,
  publication_id integer
);

ALTER TABLE screengroup
  ADD CONSTRAINT pk_screengroup
  PRIMARY KEY (id);

ALTER TABLE screengroup
  ADD CONSTRAINT fk_screengroup_screengroup
  FOREIGN KEY (screengroup_id)
  REFERENCES screengroup (id);

ALTER TABLE screengroup
  ADD CONSTRAINT fk_screengroup_publication
  FOREIGN KEY (publication_id)
  REFERENCES publication (id);
  
-- add table screen 
CREATE TABLE screen
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  description varchar(255),
  displayid varchar(255),
  name varchar(255),
  resolutionheight integer NOT NULL,
  resolutionwidth integer NOT NULL,
  touchenabled boolean,
  screengroup_id integer,
  datetimelastrequest timestamp without time zone
);

ALTER TABLE screen
  ADD CONSTRAINT pk_screen
  PRIMARY KEY (id);

ALTER TABLE screen
  ADD CONSTRAINT fk_screen_screengroup
  FOREIGN KEY (screengroup_id)
  REFERENCES screengroup (id);
