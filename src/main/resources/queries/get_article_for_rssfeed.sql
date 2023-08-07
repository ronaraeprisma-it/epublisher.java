SELECT distinct
a.id as id
, a.lastPublicationDate
, a.title
, a.prologue
, a.content
, a.sourcedate
FROM article a
LEFT JOIN article_epublisheraudiencegroup aeag ON aeag.article_id= a.id
INNER JOIN articleWrapper aW ON a.id = aw.article_id
INNER JOIN edition e ON aw.edition_id = e.id
INNER JOIN publication p ON e.publication_id = p.id
WHERE
e.dtype = 'EditionIntranetNS'
AND (aw.removedfromedition = FALSE OR aw.removedfromedition IS NULL)
AND
e.publicationDate IS NOT NULL
AND e.publicationdate >= {0}   
AND
p.dtype = 'PublicationIntranetNS'
AND
e.publication_id = {1}
order by a.sourcedate desc