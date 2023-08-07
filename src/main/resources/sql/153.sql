select  updateAllSchemaWithGivenQuery('
ALTER TABLE landingpage_form
ADD COLUMN feedback character varying;


CREATE TABLE inputfield
(
	id integer NOT NULL PRIMARY KEY,
	name character varying,
	alias character varying,
	label character varying,
	placeholder character varying,
	description character varying,
	dtype character varying,
	orderofinputfield integer,
	active boolean,
	required boolean,
	rows integer,
    entityversion integer NOT NULL
);

GRANT ALL ON TABLE inputfield to epublisher;
GRANT ALL ON TABLE inputfield to dashboard;





CREATE TABLE inputfield_options_content
(
    id integer NOT NULL PRIMARY KEY,
    orderofoption integer NOT NULL,
	content character varying(255),
	entityversion integer NOT NULL
 );

GRANT ALL ON TABLE inputfield_options_content TO epublisher;
GRANT ALL ON TABLE inputfield_options_content TO dashboard;





CREATE TABLE landingpage_form_inputfields
(
    landingpage_form_id integer NOT NULL,
    inputfield_id integer NOT NULL,
	
    CONSTRAINT fk_landingpage_form_id FOREIGN KEY (landingpage_form_id)
        REFERENCES landingpage_form (id) MATCH SIMPLE,
    CONSTRAINT fk_inputfield_id FOREIGN KEY (inputfield_id)
        REFERENCES inputfield (id) MATCH SIMPLE
);

GRANT ALL ON TABLE landingpage_form_inputfields TO epublisher;
GRANT ALL ON TABLE landingpage_form_inputfields TO dashboard;





CREATE TABLE inputfield_options
(
    inputfield_id integer NOT NULL,
    inputfield_options_content_id integer NOT NULL,

    CONSTRAINT fk_inputfield_id FOREIGN KEY (inputfield_id)
        REFERENCES inputfield (id) MATCH SIMPLE,
    CONSTRAINT fk_inputfield_options_content_id FOREIGN KEY (inputfield_options_content_id)
        REFERENCES inputfield_options_content (id) MATCH SIMPLE
);

GRANT ALL ON TABLE inputfield_options TO epublisher;
GRANT SELECT, REFERENCES ON TABLE inputfield_options TO dashboard;

CREATE TABLE landingpage_form_subscriptiongroup
(
    landingpage_form_id integer NOT NULL,
    availablesubscriptiongroups_id integer NOT NULL,
    CONSTRAINT p_asg_landingpage_form_id_asgid_pkey PRIMARY KEY (landingpage_form_id, availablesubscriptiongroups_id),
    CONSTRAINT p_asg_asgid FOREIGN KEY (availablesubscriptiongroups_id)
        REFERENCES subscriptiongroup (id) MATCH SIMPLE,
    CONSTRAINT p_landingpage_form_id FOREIGN KEY (landingpage_form_id)
        REFERENCES landingpage_form (id) MATCH SIMPLE
);

ALTER TABLE landingpage_form_subscriptiongroup
    OWNER to epublisher;

GRANT ALL ON TABLE landingpage_form_subscriptiongroup TO epublisher;
GRANT SELECT, REFERENCES ON TABLE landingpage_form_subscriptiongroup TO dashboard;

GRANT SELECT, REFERENCES ON TABLE landingpage_form TO epublisher;
GRANT SELECT, REFERENCES ON TABLE landingpage_form TO dashboard;


');