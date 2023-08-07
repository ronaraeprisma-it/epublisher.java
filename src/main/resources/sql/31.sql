--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add two columns to the edition for an email address and subject
ALTER TABLE edition ADD COLUMN emailAddress character varying(255);
ALTER TABLE edition ADD COLUMN emailSubject character varying(255);
