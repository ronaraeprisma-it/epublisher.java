--		Copyright 2016, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Reset the values of rendering template and css
UPDATE template SET freemarkertemplatetouse = 'newsletter';
UPDATE template SET csstemplatetouse = 'ns';
