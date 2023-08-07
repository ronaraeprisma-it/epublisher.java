select updateAllSchemaWithGivenQuery('
-- Table: articletts

CREATE TABLE articletts
(
    id integer PRIMARY KEY,
	entityversion integer,
    phase integer,
	name character varying(255),
	approaching character varying(255),
	approaching_suffix character varying(255),
	final_destination character varying(255),
	transfer character varying(255),
	platform character varying(255),
	change_platform character varying(255),
	timing_phase integer,
	type_stations character varying(255),
    seconds integer,
	phase_change integer
);

ALTER TABLE articletts
    OWNER to epublisher;

GRANT ALL ON TABLE articletts TO epublisher;


-- Table: article_tts

CREATE TABLE article_tts
(
    article_id integer NOT NULL,
    articletts_id integer NOT NULL,
    CONSTRAINT article_tts_pkey PRIMARY KEY (article_id, articletts_id),
    CONSTRAINT article_tts_articletts_id_key UNIQUE (articletts_id)
,
    CONSTRAINT fk32bef73d434b17qs FOREIGN KEY (articletts_id)
        REFERENCES articletts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk32bef73de0222h4d FOREIGN KEY (article_id)
        REFERENCES article (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

');
