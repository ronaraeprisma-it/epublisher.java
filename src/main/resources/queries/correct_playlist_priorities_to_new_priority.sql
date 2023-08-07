-- This query updates all non-deleted playlists that have a higher priority than the max priority as specified by the publication it belongs to.
UPDATE
	playlist
SET
	priority = (
		SELECT
			maxplaylistpriority
		FROM
			publication
		WHERE
			id = :publicationid
		)
WHERE
	publication_id = :publicationid
	AND deleted = false
	AND priority > (
		SELECT
			maxplaylistpriority
		FROM
			publication
		WHERE
			id = :publicationid
		)
