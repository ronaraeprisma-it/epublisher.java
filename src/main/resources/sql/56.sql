select  updateAllSchemaWithGivenQuery('DROP TABLE roles;
    
	CREATE TABLE Permission(id INTEGER NOT NULL, entityversion INTEGER NOT NULL,name CHARACTER VARYING(255));

	ALTER TABLE Permission
	  ADD CONSTRAINT pk_Permission
	  PRIMARY KEY (id);

	 GRANT ALL ON TABLE Permission TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE Permission TO dashboard;


	CREATE TABLE Roles(id INTEGER NOT NULL, entityversion INTEGER NOT NULL,name CHARACTER VARYING(255));

	ALTER TABLE Roles
	  ADD CONSTRAINT pk_Roles
	  PRIMARY KEY (id);

	GRANT ALL ON TABLE Roles TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE Roles TO dashboard;
	
	CREATE TABLE epublisheruser_roles(epublisheruser_id INTEGER NOT NULL, roles_id INTEGER NOT NULL, 
	PRIMARY KEY (epublisheruser_id, roles_id ), UNIQUE (epublisheruser_id ,roles_id ));

	ALTER TABLE epublisheruser_roles
	  ADD CONSTRAINT fk_epublisheruser_roles_epublisheruserid
	  FOREIGN KEY (epublisheruser_id)
	  REFERENCES epublisheruser(id);

	ALTER TABLE epublisheruser_roles
	  ADD CONSTRAINT fk_epublisheruser_roles_id
	  FOREIGN KEY (roles_id)
	  REFERENCES roles(id);

	GRANT ALL ON TABLE epublisheruser_roles TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE epublisheruser_roles TO dashboard;

	CREATE TABLE roles_permission(roles_id  INTEGER NOT NULL, permission_id INTEGER NOT NULL,read boolean , write boolean, 
	PRIMARY KEY (roles_id , permission_id ), UNIQUE (roles_id  , permission_id ));


	ALTER TABLE roles_permission
	  ADD CONSTRAINT fk_roles_permission_rolesid
	  FOREIGN KEY (roles_id)
	  REFERENCES roles(id);

	ALTER TABLE roles_permission
	  ADD CONSTRAINT fk_roles_permission_id
	  FOREIGN KEY (permission_id)
	  REFERENCES permission(id);

	GRANT ALL ON TABLE roles_permission TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE roles_permission TO dashboard;
');