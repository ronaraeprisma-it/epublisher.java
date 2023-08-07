--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Update tags to be lower case ND2-117

UPDATE article_tags SET tags = lower(tags)