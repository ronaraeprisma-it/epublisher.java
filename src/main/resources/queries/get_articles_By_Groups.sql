
SELECT distinct
article_id
,articlePosition
, articlesInEdition
,1 as editionid
,apiid 
,shareStrategy

FROM (
select p.article_id
, p.articlesInEdition
, 1
, p.apiid  as apiid
,p.sharestrategy as shareStrategy
,row_number() OVER (PARTITION BY  p.id  ORDER BY p.sourcedate DESC) as articlePosition
from 
(SELECT
DISTINCT aw.article_id
,a.sourcedate
, count( * ) OVER (PARTITION BY pg.id) AS articlesInEdition
, a.title
--, e.id as editionid
, pg.apiid  as apiid
,p.sharestrategy as shareStrategy
,pg.id as id
FROM article a
LEFT JOIN article_epublisheraudiencegroup aeag ON aeag.article_id= a.id
INNER JOIN articleWrapper aW ON a.id = aw.article_id
INNER JOIN edition e ON aw.edition_id = e.id
INNER JOIN publication p ON e.publication_id = p.id
LEFT JOIN publication_epublisheraudiencegroup peag ON peag.publication_id= p.id
INNER JOIN publication_group_publication pgp ON p.id = pgp.publications_id
INNER JOIN publication_group pg ON pgp.publication_group_id = pg.id
WHERE
e.dtype = 'EditionIntranetNS'
AND (aw.removedfromedition = FALSE OR aw.removedfromedition IS NULL)
AND

e.publicationDate IS NOT NULL
AND e.publicationdate >= {4}  
AND 
--{0} - api id
--{1} -- if there exist audience
pg.apiid IN ({0})
{1} order by a.sourceDate desc) p


) a
WHERE
--{2} - Page Number , {3} --  PageSize
articlePosition BETWEEN (({2} * {3}) + 1 ) AND (({2} + 1) * {3})
ORDER BY
apiid,
articleposition
