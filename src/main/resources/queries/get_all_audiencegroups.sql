
WITH RECURSIVE tree AS (
    SELECT 
    	root.id
    	, root.name
    	, 0 as indent
    	, lower(root.name) as sortorder
    	, cdp.piwikaudienceid as piwikId 
    FROM
     	epublisherAudiencegroup AS root left join epublisheraudiencegroup_audience_cdp as  eacdp on root.id = eacdp.epublisheraudiencegroup_id
     	left join audience_cdp as cdp on cdp.id = eacdp.availableaudience_id
    WHERE root.epublisheraudiencegroup_id IS NULL
    
    UNION ALL
    
    SELECT 
    	child.id
    	, child.name
    	, parent.indent + 1 as indent
    	, parent.sortorder || lower(child.name) as sortorder
    	, cdp.piwikaudienceid as piwikId 
    FROM epublisherAudiencegroup child left join epublisheraudiencegroup_audience_cdp as  eacdp on child.id = eacdp.epublisheraudiencegroup_id
     	left join audience_cdp as cdp on cdp.id = eacdp.availableaudience_id
    	INNER JOIN tree parent ON child.epublisheraudiencegroup_id = parent.id
)
SELECT id as id, name as name, indent as indent,  piwikId 
FROM tree
ORDER BY 
	lower(sortorder)