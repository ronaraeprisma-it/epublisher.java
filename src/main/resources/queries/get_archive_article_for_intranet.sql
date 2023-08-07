SELECT
article_id as id, title
, articlesInEdition as count

FROM (
select p.article_id,p.title
,count( * ) OVER (PARTITION BY p.publicationid) AS articlesInEdition
, 1 as editionid
, p.publicationid  as publicationid

from 
(
SELECT distinct
aw.article_id
, a.sourcedate
, a.title
, p.id as publicationid 
FROM article a
LEFT JOIN article_epublisheraudiencegroup aeag ON aeag.article_id= a.id
INNER JOIN articleWrapper aW ON a.id = aw.article_id
INNER JOIN edition e ON aw.edition_id = e.id
INNER JOIN publication p ON e.publication_id = p.id
WHERE
e.dtype = {0}
AND (aw.removedfromedition = FALSE OR aw.removedfromedition IS NULL)
AND
e.publicationDate IS NOT NULL
AND e.publicationdate >= {1}   
AND
e.publication_id = {2}
order by a.sourcedate desc)p 
) a
