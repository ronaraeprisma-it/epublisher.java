-- Recursively select all parent screengroups of a given screengroup
WITH RECURSIVE screengroups(id, name, screengroup_id, publication_id) AS (
	SELECT
			g.id
			, g.name
			, g.screengroup_id
			, publication_id
	FROM
			screengroup g
	WHERE
			g.id {0}

	UNION ALL

	SELECT
			g.id
			, g.name
			, g.screengroup_id
			, g.publication_id
	FROM
			screengroup g
			, screengroups sg
	WHERE
			g.id = sg.screengroup_id
)
-- Get all playlists and it's broadcasts that should be playing for the above screengroups, ordered by priority
-- Also retrieve the name of the publication that the playlist belongs to.
-- Also added is broadcast active check - using server time to verify
, playlists AS (
	SELECT
			sgs.id
			, sgs.name as groupname
			, screengroup_id
			, sgs.publication_id
			, p.name
			, pl.name as playlistName
			, pl.publicationdate 
			, pl.priority
			, pl.id as playlistId
			, b.orderofbroadcast
			, b.id as broadcastwrapper_id
			, b.broadcast_id
			, br.lastChangedDate as broadcastChangedDate
	FROM
			screengroups sgs
			LEFT JOIN publication p on (sgs.publication_id = p.id)
			LEFT JOIN playlist pl on (pl.publication_id = p.id)
			LEFT JOIN playlist_broadcastwrapper plb on (pl.id = plb.playlist_id)
			LEFT JOIN broadcastwrapper b on (plb.broadcastwrappers_id = b.id)
			LEFT JOIN broadcast br on (b.broadcast_id = br.id)
	WHERE
			p.deleted = false
			AND pl.deleted = false
			AND	pl.publicationdate IS NOT NULL
			AND br.template_id IS NOT NULL
			AND br.deleted is false
			AND b.deleted is false
			AND (br.displayStartDate IS NULL OR br.displayStartDate <= current_date) AND (br.displayEndDate IS NULL OR current_date <= br.displayEndDate)
	ORDER BY
			pl.priority DESC
			, pl.id
			, b.orderofbroadcast ASC
)
-- Will be replaced by whatever else you want to use this base query for
{1}