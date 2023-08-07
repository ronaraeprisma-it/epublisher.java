select  updateAllSchemaWithGivenQuery('CREATE TABLE profileauthors (searchobject_id INT NOT NULL , epublisheruser_id INT NOT NULL);

ALTER TABLE profileauthors
  ADD CONSTRAINT profileauthors_searchobjectid 
  FOREIGN KEY ("searchobject_id") 
  REFERENCES searchobject ("id");

ALTER TABLE profileauthors
  ADD CONSTRAINT profileauthors_epubuserid 
  FOREIGN KEY ("epublisheruser_id") 
  REFERENCES epublisheruser ("id"); 
')
