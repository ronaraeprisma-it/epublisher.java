--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Change the data type of playtime from integer to timestamp - ND2-110

--ALTER TABLE broadcastplay ALTER COLUMN playtime TYPE timestamp without time zone USING playtime::timestamp without time zone;

-- Create a temporary TIMESTAMP column
ALTER TABLE broadcastplay ADD COLUMN create_time_holder TIMESTAMP without time zone NULL;

-- Drop the existing column 
ALTER TABLE broadcastplay DROP COLUMN playtime;

--Rename the column
ALTER TABLE broadcastplay RENAME COLUMN create_time_holder to playtime ;