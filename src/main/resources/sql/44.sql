--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Include prologue in Edition class


ALTER TABLE Edition ADD COLUMN prologueText text;

-- update the already sent edition with thier prologue text

UPDATE  edition SET prologueText=subquery.prologue
FROM  (select e.id as id,t.prologuetext as prologue from template t inner join publication p on t.id = p.template_id inner join edition e on p.id = e.publication_id) as subquery
WHERE  edition.id=subquery.id;