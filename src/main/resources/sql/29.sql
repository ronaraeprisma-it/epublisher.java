--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add two columns to the publication for an email address and subject (ND-342)
ALTER TABLE publication ADD COLUMN emailAddress character varying(255);
ALTER TABLE publication ADD COLUMN emailSubject character varying(255);
