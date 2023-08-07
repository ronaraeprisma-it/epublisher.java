select updateAllSchemaWithGivenQuery('
CREATE TABLE group_publication (
	id integer NOT NULL PRIMARY KEY, 
	groupid integer, 
	publicationid integer NOT NULL, 
	publicationorder integer NOT NULL, 
	entityversion integer NOT NULL
);

CREATE TABLE groupwrapper (
	id integer NOT NULL PRIMARY KEY, 
	groupname CHARACTER VARYING(255), 
	groupsequencenr integer NOT NULL, 
	groupDType CHARACTER VARYING(255), 
	userId integer NOT NULL,
	entityversion integer NOT NULL
);

ALTER TABLE group_publication
  ADD CONSTRAINT groupPublication_ID
  FOREIGN KEY(groupid)
  REFERENCES  groupwrapper(id);
	

');