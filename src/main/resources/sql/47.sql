--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Insert Image in Multipage template NS_EPB-1176

insert into containerarea_allowedcontent(containerarea_id,allowedcontent ) (select tca.containerareas_id, 'afbeelding' from containerarea a join templatenarrowcasting_containerarea tca on a.id = tca.containerareas_id join templatenarrowcasting tn on tca.templatenarrowcasting_id = tn.id where tn.htmltemplate = 'nedtrain-multipage' and a.containerposition in (1,2,3,4,5,6,7,8) )