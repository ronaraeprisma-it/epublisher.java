--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add four columns to the epublishermedia for username, uuid, folderid, version
ALTER TABLE epublishermedia ADD COLUMN username character varying(255);
ALTER TABLE epublishermedia ADD COLUMN uuid character varying(255);
ALTER TABLE epublishermedia ADD COLUMN folderid bigint;
ALTER TABLE epublishermedia ADD COLUMN version character varying(75);