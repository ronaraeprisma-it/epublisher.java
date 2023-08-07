CREATE TABLE epublisheruser_templatenarrowcasting
(
  epublisheruser_id integer NOT NULL,
  availabletemplates_id integer NOT NULL,
  CONSTRAINT epublisheruser_templatenarrowcasting_pkey PRIMARY KEY (epublisheruser_id, availabletemplates_id),
  CONSTRAINT epu_tn_epu_epuid_fkey FOREIGN KEY (epublisheruser_id)
      REFERENCES epublisheruser (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT epu_tn_tn_atnid_fkey FOREIGN KEY (availabletemplates_id)
      REFERENCES templatenarrowcasting (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE epublisheruser_templatenarrowcasting
  OWNER TO nsliferay;

-- give all existing users access to epublisher templates  
INSERT INTO epublisheruser_templatenarrowcasting(
            epublisheruser_id, availabletemplates_id)    
SELECT us.id, tm.id
  FROM epublisheruser us, templatenarrowcasting tm;