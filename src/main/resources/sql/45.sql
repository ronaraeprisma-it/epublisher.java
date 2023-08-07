--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Change the data type of allowedBrightcoveFolders from integer to string 

ALTER TABLE epublisheruser_allowedBrightcoveFolders ALTER COLUMN allowedBrightcoveFolders TYPE varchar(255);