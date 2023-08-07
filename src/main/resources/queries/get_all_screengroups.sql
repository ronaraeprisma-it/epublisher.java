WITH RECURSIVE tree AS (
    SELECT 
    	root.id
    	, root.name
    	, 0 as indent
    	, lower(root.name) as sortorder
    FROM
     	screengroup AS root
    WHERE root.screengroup_id IS NULL
    
    UNION ALL
    
    SELECT 
    	child.id
    	, child.name
    	, parent.indent + 1 as indent
    	, parent.sortorder || lower(child.name) as sortorder
    FROM screengroup child
    	INNER JOIN tree parent ON child.screengroup_id = parent.id
)
SELECT id as id, name as name, indent as indent
FROM tree
ORDER BY 
	lower(sortorder)
