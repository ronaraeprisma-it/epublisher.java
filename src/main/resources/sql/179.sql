select updateAllSchemaWithGivenQuery('

CREATE TABLE LabelGroup
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  name character varying(255)
  
);

ALTER TABLE LabelGroup
  ADD CONSTRAINT pk_LabelGroup
  PRIMARY KEY (id);

GRANT ALL ON TABLE LabelGroup TO epublisher;
GRANT SELECT ON TABLE LabelGroup TO dashboard;
GRANT SELECT ON TABLE LabelGroup TO development;

  
  
  
CREATE TABLE Label
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  name character varying(255)
);

ALTER TABLE Label
  ADD CONSTRAINT pk_Label
  PRIMARY KEY (id);

GRANT ALL ON TABLE Label TO epublisher;
GRANT SELECT ON TABLE Label TO dashboard;
GRANT SELECT ON TABLE Label TO development;


  

CREATE TABLE labelgroup_label
(
  labelgroup_id  integer NOT NULL,
  labels_id integer NOT NULL
);



GRANT ALL ON TABLE labelgroup_label TO epublisher;
GRANT SELECT ON TABLE labelgroup_label TO dashboard;
GRANT SELECT ON TABLE labelgroup_label TO development;

ALTER TABLE labelgroup_label
  ADD CONSTRAINT fk_labelgroup_id
  FOREIGN KEY(labelgroup_id)
  REFERENCES  LabelGroup(id);
  
ALTER TABLE labelgroup_label
  ADD CONSTRAINT fk_labels_id
  FOREIGN KEY(labels_id)
  REFERENCES  Label(id);
  




CREATE TABLE article_labels
(
  article_id  integer NOT NULL,
  labels_id integer NOT NULL
);



GRANT ALL ON TABLE article_labels TO epublisher;
GRANT SELECT ON TABLE article_labels TO dashboard;
GRANT SELECT ON TABLE article_labels TO development;

ALTER TABLE article_labels
  ADD CONSTRAINT fk_article_id
  FOREIGN KEY(article_id)
  REFERENCES  article(id);
  
ALTER TABLE article_labels
  ADD CONSTRAINT fk_labels_id
  FOREIGN KEY(labels_id)
  REFERENCES  Label(id);
  
  
CREATE TABLE epublisheruser_labelgroup
(
  epublisheruser_id  integer NOT NULL,
  availablelabelgroup_id integer NOT NULL
);



GRANT ALL ON TABLE epublisheruser_labelgroup TO epublisher;
GRANT SELECT ON TABLE epublisheruser_labelgroup TO dashboard;
GRANT SELECT ON TABLE epublisheruser_labelgroup TO development;

ALTER TABLE epublisheruser_labelgroup
  ADD CONSTRAINT fk_epublisheruser_id
  FOREIGN KEY(epublisheruser_id)
  REFERENCES  epublisheruser(id);
  
ALTER TABLE epublisheruser_labelgroup
  ADD CONSTRAINT fk_availablelabelgroup_id
  FOREIGN KEY(availablelabelgroup_id)
  REFERENCES  LabelGroup(id);
');