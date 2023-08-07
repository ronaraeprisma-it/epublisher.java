select  updateAllSchemaWithGivenQuery(
'
CREATE TABLE IF NOT EXISTS booking (
   id integer NOT NULL PRIMARY KEY,
   externalPlaylistId VARCHAR ( 255 ),
   startDate TIMESTAMP without time zone,
   endDate TIMESTAMP without time zone
);

CREATE TABLE IF NOT EXISTS booking_broadcast (
   booking_id integer,
   broadcasts_id integer,
   PRIMARY KEY (booking_id , broadcasts_id ), UNIQUE (booking_id  , broadcasts_id )
);

ALTER TABLE booking_broadcast ADD CONSTRAINT fk_booking_broadcast_bookingid  FOREIGN KEY (booking_id)  REFERENCES  booking(id);
ALTER TABLE booking_broadcast ADD CONSTRAINT fk_booking_broadcast_broadcastsid  FOREIGN KEY (broadcasts_id)  REFERENCES  broadcast(id);
'           
); 

