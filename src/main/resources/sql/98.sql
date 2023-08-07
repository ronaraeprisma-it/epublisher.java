select  updateAllSchemaWithGivenQuery('

CREATE TABLE articletype(id INTEGER NOT NULL, name character varying(255), entityversion integer NOT NULL DEFAULT 1,
PRIMARY KEY (id), UNIQUE (id));

GRANT ALL ON TABLE articletype TO epublisher;
GRANT SELECT, REFERENCES ON TABLE articletype TO dashboard;

CREATE TABLE article_articletype(article_id  INTEGER NOT NULL, articletype_id INTEGER NOT NULL,
PRIMARY KEY (article_id , articletype_id ), UNIQUE (article_id  , articletype_id ));
ALTER TABLE article_articletype  ADD CONSTRAINT fk_article_articletype_articleid  FOREIGN KEY (article_id)  REFERENCES  article(id);
ALTER TABLE article_articletype  ADD CONSTRAINT fk_article_articletype_id  FOREIGN KEY (articletype_id)  REFERENCES  articletype(id);

GRANT ALL ON TABLE article_articletype TO epublisher;
GRANT SELECT, REFERENCES ON TABLE article_articletype TO dashboard;

CREATE TABLE epublisheruser_articletype(epublisheruser_id  INTEGER NOT NULL, availablearticletypes_id INTEGER NOT NULL,
PRIMARY KEY (epublisheruser_id , availablearticletypes_id ), UNIQUE (epublisheruser_id  , availablearticletypes_id ));
ALTER TABLE epublisheruser_articletype  ADD CONSTRAINT fk_epublisheruser_articletype_epublisheruserid  FOREIGN KEY (epublisheruser_id)  REFERENCES  epublisheruser(id);
ALTER TABLE epublisheruser_articletype  ADD CONSTRAINT fk_epublisheruser_articletype_id  FOREIGN KEY (availablearticletypes_id)  REFERENCES  articletype(id);

GRANT ALL ON TABLE epublisheruser_articletype TO epublisher;
GRANT SELECT, REFERENCES ON TABLE epublisheruser_articletype TO dashboard;
')