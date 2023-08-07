select updateAllSchemaWithGivenQuery('

	CREATE TABLE location
	(
	  id integer NOT NULL,
	  name character varying(255),
	  entityversion integer NOT NULL DEFAULT 1,
	  deleted boolean NOT NULL DEFAULT false,
	  CONSTRAINT location_pkey PRIMARY KEY (id)
	);
	
	ALTER TABLE location
	  OWNER TO epublisher;
	GRANT ALL ON TABLE location TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE location TO dashboard;


	CREATE TABLE route_location
	(
	  route_id integer NOT NULL,
	  location_id integer NOT NULL,
	  CONSTRAINT route_location_pkey PRIMARY KEY (route_id, location_id),
	  CONSTRAINT fk_route_location_id FOREIGN KEY (location_id)
		  REFERENCES location (id) MATCH SIMPLE
		  ON UPDATE NO ACTION ON DELETE NO ACTION,
	  CONSTRAINT fk_epublisheruser_location_routeid FOREIGN KEY (route_id)
		  REFERENCES route (id) MATCH SIMPLE
		  ON UPDATE NO ACTION ON DELETE NO ACTION
	);
	
	ALTER TABLE route_location
	  OWNER TO epublisher;
	GRANT ALL ON TABLE route_location TO epublisher;
	GRANT SELECT, REFERENCES ON TABLE route_location TO dashboard;

');