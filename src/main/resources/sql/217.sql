select  updateAllSchemaWithGivenQuery('
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''inleiding'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''labels'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''afbeeldingen'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''thumbnails'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''video'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''bestanden'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''mijnberichten'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''doelgroepen'', ''article'');
INSERT INTO available_publication_tabs(
	id, entityversion, name, type)
	VALUES (nextval(''epublisher_sequence''), 1,  ''locatie'', ''playlist'');


INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationNewsletterNS'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationNarrowcastingNS'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationIntranetNS'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationFacebook'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationLinkedin'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationPocketRailNS'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationInternet'');
INSERT INTO available_publication_types(
	id, entityversion, dtype)
	VALUES (nextval(''epublisher_sequence''), 1,  ''PublicationLandingPage'');

');
