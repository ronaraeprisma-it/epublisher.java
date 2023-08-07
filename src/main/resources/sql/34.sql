--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- add sharestrategy to publication and default its value
-- alter table publication add sharestrategy character varying(25) default 'SHARESTRATEGYPRIVATE';
-- update publication set sharestrategy = 'SHARESTRATEGYPRIVATE';