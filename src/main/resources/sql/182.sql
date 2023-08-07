select updateAllSchemaWithGivenQuery('

CREATE TABLE epublisherAudienceGroup
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  name character varying(255),
  description character varying(255),
  EpublisherAudienceGroup_id integer
);

ALTER TABLE epublisherAudienceGroup
  ADD CONSTRAINT pk_epublisherAudienceGroup
  PRIMARY KEY (id);

ALTER TABLE epublisherAudienceGroup
  ADD CONSTRAINT fk_epublisherAudienceGroup
  FOREIGN KEY (EpublisherAudienceGroup_id)
  REFERENCES epublisherAudienceGroup (id);



GRANT ALL ON TABLE epublisherAudienceGroup TO epublisher;
GRANT SELECT ON TABLE epublisherAudienceGroup TO dashboard;
GRANT SELECT ON TABLE epublisherAudienceGroup TO development;


CREATE TABLE Audience_CDP
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  name character varying(255),
  piwikaudienceId character varying(255)
);


ALTER TABLE Audience_CDP
  ADD CONSTRAINT pk_AudienceCDP
  PRIMARY KEY (id);


  
GRANT ALL ON TABLE Audience_CDP TO epublisher;
GRANT SELECT ON TABLE Audience_CDP TO dashboard;
GRANT SELECT ON TABLE Audience_CDP TO development;
 

CREATE TABLE Profile_cdp
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  firstname character varying(255),
  lastname character varying(255),
  middlename character varying(255),
  email character varying(255)
);


ALTER TABLE Profile_cdp
  ADD CONSTRAINT pk_Profile_cdp
  PRIMARY KEY (id);
  
GRANT ALL ON TABLE Profile_cdp TO epublisher;
GRANT SELECT ON TABLE Profile_cdp TO dashboard;
GRANT SELECT ON TABLE Profile_cdp TO development;
  


CREATE TABLE publication_epublisheraudiencegroup
(
  publication_id  integer NOT NULL,
  availableAudienceGroup_id integer NOT NULL
);



GRANT ALL ON TABLE publication_epublisheraudiencegroup TO epublisher;
GRANT SELECT ON TABLE publication_epublisheraudiencegroup TO dashboard;
GRANT SELECT ON TABLE publication_epublisheraudiencegroup TO development;

ALTER TABLE publication_epublisheraudiencegroup
  ADD CONSTRAINT fk_publication_id
  FOREIGN KEY(publication_id)
  REFERENCES  publication(id);
  
ALTER TABLE publication_epublisheraudiencegroup
  ADD CONSTRAINT fk_availableAudienceGroup_id
  FOREIGN KEY(availableAudienceGroup_id)
  REFERENCES  epublisheraudiencegroup(id);
  
  
 CREATE TABLE article_epublisheraudiencegroup
(
  article_id  integer NOT NULL,
  availableaudiencegroup_id integer NOT NULL
);



GRANT ALL ON TABLE article_epublisheraudiencegroup TO epublisher;
GRANT SELECT ON TABLE article_epublisheraudiencegroup TO dashboard;
GRANT SELECT ON TABLE article_epublisheraudiencegroup TO development;

ALTER TABLE article_epublisheraudiencegroup
  ADD CONSTRAINT fk_article_id
  FOREIGN KEY(article_id)
  REFERENCES  article(id);
  
ALTER TABLE article_epublisheraudiencegroup
  ADD CONSTRAINT fk_epublisheraudiencegroup_id
  FOREIGN KEY(availableaudiencegroup_id)
  REFERENCES  epublisherAudienceGroup(id);
  

CREATE TABLE audience_cdp_profile_cdp 
(
  availableprofile_id  integer NOT NULL,
  audience_cdp_id integer NOT NULL
);



GRANT ALL ON TABLE audience_cdp_profile_cdp TO epublisher;
GRANT SELECT ON TABLE audience_cdp_profile_cdp TO dashboard;
GRANT SELECT ON TABLE audience_cdp_profile_cdp TO development;

ALTER TABLE audience_cdp_profile_cdp
  ADD CONSTRAINT fk_profilecdp_id
  FOREIGN KEY(availableprofile_id)
  REFERENCES  profile_cdp(id);
  
ALTER TABLE audience_cdp_profile_cdp
  ADD CONSTRAINT fk_audiencecdp_id
  FOREIGN KEY(audience_cdp_id)
  REFERENCES  audience_cdp(id);
  

  
CREATE TABLE epublisheraudiencegroup_audience_cdp
(
  epublisheraudiencegroup_id  integer NOT NULL,
  availableaudience_id integer NOT NULL
);



GRANT ALL ON TABLE epublisheraudiencegroup_audience_cdp TO epublisher;
GRANT SELECT ON TABLE epublisheraudiencegroup_audience_cdp TO dashboard;
GRANT SELECT ON TABLE epublisheraudiencegroup_audience_cdp TO development;

ALTER TABLE epublisheraudiencegroup_audience_cdp
  ADD CONSTRAINT fk_epublisheraudiencegroup_id
  FOREIGN KEY(epublisheraudiencegroup_id)
  REFERENCES  epublisheraudiencegroup(id);
  
ALTER TABLE epublisheraudiencegroup_audience_cdp
  ADD CONSTRAINT fk_audiencecdp_id
  FOREIGN KEY(availableaudience_id)
  REFERENCES  audience_cdp(id);
  

ALTER TABLE edition ADD COLUMN parentEdition_id integer;
    
  
 	
ALTER TABLE edition
  ADD CONSTRAINT fk_parentEdition_id
  FOREIGN KEY (parentEdition_id)
  REFERENCES edition(id);
  
  
CREATE TABLE edition_audiencecdp
(
  edition_id  integer NOT NULL,
  audience_id integer NOT NULL
);



GRANT ALL ON TABLE edition_audiencecdp TO epublisher;
GRANT SELECT ON TABLE edition_audiencecdp TO dashboard;
GRANT SELECT ON TABLE edition_audiencecdp TO development;

ALTER TABLE edition_audiencecdp
  ADD CONSTRAINT fk_edition_id
  FOREIGN KEY(edition_id)
  REFERENCES  edition(id);
  
ALTER TABLE edition_audiencecdp
  ADD CONSTRAINT fk_audience_id
  FOREIGN KEY(audience_id)
  REFERENCES  Audience_CDP(id);
  
    
CREATE TABLE Subscribe 
(
id  integer NOT NULL,
 entityversion integer NOT NULL,
 email  character varying(255),
  unsubscribed boolean NOT NULL
);



ALTER TABLE subscribe ADD CONSTRAINT email UNIQUE (email);
    

     GRANT ALL ON TABLE epublisher_sequence TO dashboard;    
 GRANT ALL ON TABLE subscribe TO dashboard;    



CREATE TABLE edition_profilescdp
(
  edition_id  integer NOT NULL,
   availableProfile_id integer NOT NULL
);



GRANT ALL ON TABLE edition_profilescdp TO epublisher;
GRANT SELECT ON TABLE edition_profilescdp TO dashboard;
GRANT SELECT ON TABLE edition_profilescdp TO development;

ALTER TABLE edition_profilescdp
  ADD CONSTRAINT fk_edition_id
  FOREIGN KEY(edition_id)
  REFERENCES  edition(id);
  
ALTER TABLE edition_profilescdp
  ADD CONSTRAINT fk_profiles_id
  FOREIGN KEY(availableProfile_id)
  REFERENCES  Profile_cdp(id);
  

 

    ');