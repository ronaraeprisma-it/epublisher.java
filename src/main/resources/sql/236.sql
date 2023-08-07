select  updateAllSchemaWithGivenQuery('
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''trein'', ''playlist'');

INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationNSTreinen'');
');
