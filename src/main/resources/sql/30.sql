--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add a column to template whether to show the source(s) or not (NS_EPB-241)
ALTER TABLE template ADD COLUMN showSourcesList boolean NOT NULL DEFAULT true;
