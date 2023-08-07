--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Create broadcastplay table to store actual plays of the various broadcasts ND2-110

CREATE TABLE broadcastplay
(
  id integer NOT NULL,
  entityversion integer NOT NULL,
  playtime integer NOT NULL,
  screenid integer NOT NULL,
  publicationid integer NOT NULL,
  playlistid integer NOT NULL,
  broadcastid integer NOT NULL
);

ALTER TABLE broadcastplay
  ADD CONSTRAINT pk_broadcastplay
  PRIMARY KEY(id);

GRANT ALL ON TABLE epublisher.broadcastplay TO epublisher;
GRANT SELECT ON TABLE epublisher.broadcastplay TO dashboard;
GRANT SELECT ON TABLE epublisher.broadcastplay TO development;

--ALTER TABLE broadcastplay
--  ADD CONSTRAINT fk_broadcastplay_screen
--  FOREIGN KEY(screenid)
--  REFERENCES screen(id);
--  
--ALTER TABLE broadcastplay
--	ADD CONSTRAINT fk_broadcastplay_publication
--	FOREIGN KEY(publicationid)
--	REFERENCES publication(id);
--	
--ALTER TABLE broadcastplay
--	ADD CONSTRAINT fk_broadcastplay_playlist
--	FOREIGN KEY(playlistid)
--	REFERENCES playlist(id);
--
--ALTER TABLE broadcastplay
--	ADD CONSTRAINT fk_broadcastplay_broadcast
--	FOREIGN KEY(broadcastid)
--	REFERENCES broadcast(id);