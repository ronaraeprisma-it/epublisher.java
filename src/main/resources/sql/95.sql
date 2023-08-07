select  updateAllSchemaWithGivenQuery('

ALTER TABLE permission ADD COLUMN nameid VARCHAR;

INSERT INTO permission (id,entityversion,name,nameid) VALUES (nextval(''epublisher_sequence''), 0, ''Alle Auteurs'', ''all-authors'');

CREATE TABLE roles_author(roles_id  INTEGER NOT NULL, author_id INTEGER NOT NULL,read boolean ,
PRIMARY KEY (roles_id , author_id ), UNIQUE (roles_id  , author_id ));
ALTER TABLE roles_author  ADD CONSTRAINT fk_roles_author_rolesid  FOREIGN KEY (roles_id)  REFERENCES  roles(id);
ALTER TABLE roles_author  ADD CONSTRAINT fk_roles_author_id  FOREIGN KEY (author_id)  REFERENCES  epublisheruser(id);

GRANT ALL ON TABLE roles_author TO epublisher;
GRANT SELECT, REFERENCES ON TABLE roles_author TO dashboard;

')