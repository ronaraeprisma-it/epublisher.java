select updateAllSchemaWithGivenQuery('
CREATE TABLE landingpage_form_submission
(
    id integer NOT NULL,
    name character varying,
    content character varying,
	subscriptiongroupid integer NOT NULL,
	subscriber_email character varying NOT NULL
);

GRANT ALL ON TABLE landingpage_form_submission TO epublisher;
GRANT ALL ON TABLE landingpage_form_submission TO dashboard;

');