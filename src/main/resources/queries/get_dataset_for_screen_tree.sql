WITH RECURSIVE tree AS (
  SELECT
    sg.id
    , sg.id as topid
    , sg.name
    , 1 as level
    , p.id AS publicationID
    , p.name AS publication
    , p.maxplaylistpriority
  FROM
    screengroup sg
    LEFT JOIN publication p ON sg.publication_id = p.id
  WHERE
    screengroup_id {0} -- Will be replaced with filtering conditions 

  UNION ALL

  SELECT
    sg.id
    , t.topid
    , sg.name
    , t.level + 1
    , p.id AS publicationID
    , p.name AS publication
    , p.maxplaylistpriority
  FROM
    tree t
    INNER JOIN screengroup sg on t.id = sg.screengroup_id
    LEFT JOIN publication p ON sg.publication_id = p.id
),
combined AS (
  SELECT
    sg.id
    , sg.screengroup_id AS parentID
    , sg.name
    , 'ScreenGroup' AS dtype
    , p.name AS publication
    , p.id AS publicationID
    , p.maxplaylistpriority
  FROM
    screengroup sg
    LEFT JOIN publication p ON sg.publication_id = p.id
  
  UNION
  
  SELECT
    id
    , screengroup_id AS parentID
    , name
    , 'Screen' AS dtype
    , NULL
    , NULL
    , NULL
  FROM
    screen
)
SELECT
  c.*
  , (SELECT COUNT( * ) FROM tree t WHERE t.topid = c.id AND t.id <> t.topid AND t.publicationID {1} ) AS selectedDescendants
FROM
  combined c
WHERE
  parentID {2} -- Will be replaced with filtering conditions 
ORDER BY
  lower(name)