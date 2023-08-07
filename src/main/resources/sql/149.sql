select  updateAllSchemaWithGivenQuery('
CREATE TABLE landingpage_form (id integer NOT NULL PRIMARY KEY, name varchar NOT NULL, entityversion integer NOT NULL);




CREATE TABLE landingpage (id integer NOT NULL PRIMARY KEY, startdate timestamp without time zone,	enddate timestamp without time zone, form_id integer, entityversion integer NOT NULL);

ALTER TABLE landingpage
		ADD CONSTRAINT landingpage_landingpage_form_id 
		FOREIGN KEY (form_id)
        REFERENCES landingpage_form (id) MATCH SIMPLE;




CREATE table publication_landingpage (publication_id integer NOT NULL, landingpage_id integer NOT NULL);

ALTER TABLE publication_landingpage
	ADD CONSTRAINT publication_landingpage_publication_id_landingpage__key 
	UNIQUE (publication_id, landingpage_id);

ALTER TABLE publication_landingpage
    ADD CONSTRAINT fk_publication_landingpage_id 
	FOREIGN KEY (landingpage_id)
    REFERENCES landingpage (id) MATCH SIMPLE;

ALTER TABLE publication_landingpage
    ADD CONSTRAINT fk_publication_landingpage_publicationid 
	FOREIGN KEY (publication_id)
    REFERENCES publication (id) MATCH SIMPLE;





CREATE TABLE landingpage_articles (publication_id integer NOT NULL, article_id integer NOT NULL);

ALTER TABLE landingpage_articles
	ADD CONSTRAINT fk_publication_publication_id 
	FOREIGN KEY (publication_id)
	REFERENCES publication (id) MATCH SIMPLE;

ALTER TABLE landingpage_articles
    ADD CONSTRAINT fk_publication_landingpage_article_id 
	FOREIGN KEY (article_id)
    REFERENCES article (id) MATCH SIMPLE;

GRANT ALL ON TABLE landingpage_articles TO epublisher;
GRANT ALL ON TABLE landingpage_articles TO dashboard;

');