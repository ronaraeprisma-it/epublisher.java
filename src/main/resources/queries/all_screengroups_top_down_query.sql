WITH RECURSIVE screengroups(id, name, description, screengroup_id, publication_id, treelevel) AS (
	SELECT
			parent.id
			, parent.name
			, parent.description
			, parent.screengroup_id
			, parent.publication_id
			, 0 as treelevel
			, lower(parent.name) as sortorder
	FROM
			screengroup parent
	WHERE
			parent.screengroup_id is null

	UNION ALL

	SELECT
			child.id
			, child.name
			, child.description
			, child.screengroup_id
			, child.publication_id
			, treelevel+1 as treelevel
			, lower(sortorder) || lower(child.name) as sortorder
	FROM
			screengroup child
			, screengroups sg
	WHERE
			sg.id = child.screengroup_id
)
SELECT
		DISTINCT sgs.*
FROM
		screengroups sgs
		-- Will be replaced with filtering conditions if defined
		{0}
ORDER BY
		sortorder
