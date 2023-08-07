--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add a column to the epublisheruser for a one-time password
ALTER TABLE epublisheruser ADD COLUMN passwordNonce character varying(255);

-- set the default screen to publish for everybody who had the dashboard as default screen
UPDATE epublisheruser
SET preferedscreen = 'publish'
WHERE preferedscreen = 'dashboard';
