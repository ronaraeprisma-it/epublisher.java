WITH RECURSIVE tree AS (
  SELECT
    ag.id
    , ag.id as topid
    , ag.name
    , 1 as level
    , null
    , null as piwikid
  FROM
    epublisheraudiencegroup ag
    LEFT JOIN publication_epublisheraudiencegroup pag ON ag.id = pag.availableAudienceGroup_id
  WHERE
    epublisheraudiencegroup_id {0} -- Will be replaced with filtering conditions 

  UNION ALL

  SELECT
    ag.id
    , t.topid
    , ag.name
    , t.level + 1
    , null
    , null as piwikid
  FROM
    tree t
    INNER JOIN epublisheraudiencegroup ag on t.id = ag.epublisheraudiencegroup_id
    LEFT JOIN publication_epublisheraudiencegroup pag  ON ag.id = pag.availableAudienceGroup_id
),
combined AS (
  SELECT
    ag.id
    , ag.epublisheraudiencegroup_id AS parentID
    , ag.name
    , 'AudienceGroup' AS dtype
    , null
    , null as piwikid
  FROM
    epublisheraudiencegroup ag
    LEFT JOIN publication_epublisheraudiencegroup pag  ON ag.id = pag.availableAudienceGroup_id
  
  UNION
  
  SELECT
    acd.id
    , eacd.epublisheraudiencegroup_id AS parentID
    , acd.name
    , 'Audience' AS dtype 
    , NULL
    , acd.piwikaudienceid as piwikid
    
  FROM
    audience_cdp acd inner join epublisheraudiencegroup_audience_cdp eacd on acd.id= eacd.availableaudience_id
)
SELECT
  c.*
  , (SELECT COUNT( * ) FROM tree t WHERE t.topid = c.id AND t.id <> t.topid) AS selectedDescendants
FROM
  combined c
WHERE
  parentID {1} -- Will be replaced with filtering conditions 
ORDER BY
  lower(name)