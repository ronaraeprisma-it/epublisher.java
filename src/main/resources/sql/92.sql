select  updateAllSchemaWithGivenQuery('

CREATE TABLE IF NOT EXISTS epublisheruser_favoritearticles (

article_id INT NOT NULL , 
epublisheruser_id INT NOT NULL,

CONSTRAINT favarticle_articleid FOREIGN KEY ("article_id") REFERENCES article ("id"),
CONSTRAINT favarticle_epubuserid FOREIGN KEY ("epublisheruser_id") REFERENCES epublisheruser ("id") );

ALTER TABLE epublisheruser 
DROP COLUMN IF EXISTS "showonlyfavoritearticles";

ALTER TABLE epublisheruser
  ADD COLUMN "showonlyfavoritearticles" BOOLEAN
  DEFAULT false;
  
')