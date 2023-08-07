SELECT
article_id
, articlePosition
, articlesInEdition
, editionid
, publicationid
,shareStrategy
,removedfromedition
,urgent
FROM (
select p.article_id, p.removedfromedition
,p.urgent
,count( * ) OVER (PARTITION BY p.publicationid) AS articlesInEdition
, 1 as editionid
, p.publicationid  as publicationid

,p.sharestrategy as shareStrategy
,row_number() OVER (PARTITION BY  p.publicationid ORDER BY p.sourcedate DESC) as articlePosition
from 
(
SELECT distinct
aw.article_id
,aw.removedfromedition
,aw.urgent
, a.sourcedate
--, count( * ) OVER (PARTITION BY e.id) AS articlesInEdition
, a.title

, p.id as publicationid
,p.sharestrategy as shareStrategy
FROM article a
LEFT JOIN article_epublisheraudiencegroup aeag ON aeag.article_id= a.id
INNER JOIN articleWrapper aW ON a.id = aw.article_id
INNER JOIN edition e ON aw.edition_id = e.id
INNER JOIN publication p ON e.publication_id = p.id
LEFT JOIN publication_epublisheraudiencegroup peag ON peag.publication_id= p.id
WHERE
e.dtype = 'EditionIntranetNS'
--AND (aw.removedfromedition = FALSE OR aw.removedfromedition IS NULL)
AND
e.publicationDate IS NOT NULL
AND e.publicationdate >= {4}    
AND
--{0} - publication id 
--{1} - if there exist audience
e.publication_id IN ({0})
{1}
{5}

order by a.sourcedate desc)p 
) a
WHERE
--{2} - Page Number , {3} --  PageSize
articlePosition BETWEEN ({2} *{3} ) + 1 AND ({2} + 1) * {3}
ORDER BY
editionid
, articlePosition