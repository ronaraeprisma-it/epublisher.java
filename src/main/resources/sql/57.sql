select  updateAllSchemaWithGivenQuery('INSERT INTO roles (id, entityversion, name) VALUES (nextval(''epublisher_sequence''), 0, ''ROLE_ADMIN'');
INSERT INTO epublisheruser_roles (epublisheruser_id, roles_id) VALUES ((select max(id) from epublisheruser where email =''hosting@prisma-it.com''), (Select max(id) FROM Roles where name= ''ROLE_ADMIN''));
INSERT INTO roles (id, entityversion, name) VALUES (nextval(''epublisher_sequence''), 0, ''ROLE_USER'');
INSERT INTO epublisheruser_roles (epublisheruser_id, roles_id) VALUES ((select max(id) from epublisheruser where email =''hosting@prisma-it.com''), (Select max(id) FROM Roles where name= ''ROLE_USER''));
');



