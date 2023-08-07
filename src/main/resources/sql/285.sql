select  updateAllSchemaWithGivenQuery(
'
CREATE TABLE IF NOT EXISTS booking (
   id integer NOT NULL PRIMARY KEY,
   entityversion integer NOT NULL,
   broadcast_id integer,
   startDate TIMESTAMP without time zone,
   endDate TIMESTAMP without time zone,
   lastUpdated TIMESTAMP without time zone,
   state VARCHAR ( 255 ),
   playlistIds VARCHAR ( 255 )
);

ALTER TABLE booking 
	ADD CONSTRAINT fk_booking_brodcast_id
	FOREIGN KEY ("broadcast_id") 
	REFERENCES broadcast ("id");
'      

); 