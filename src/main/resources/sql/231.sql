select  updateAllSchemaWithGivenQuery('
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''texttospeech'', ''article'');

INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationTTS'');

INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationPortalPage'');
');
