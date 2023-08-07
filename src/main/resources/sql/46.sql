--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- remove all trailing non alohanumeric characters ND2-117

UPDATE  article_tags as a SET (article_id,tags)=(select article_id ,RTRIM(tags, '%,@#$%^&*;:./|\=+%')  from article_tags  where article_tags.article_id = a.article_id and article_tags.tags =a.tags ) 

