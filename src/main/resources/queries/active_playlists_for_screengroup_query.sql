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
			, sgs.screengroup_id
			, sgs.publication_id
			, p.name
			, pls.playlist_id
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
			LEFT JOIN playlist_screengroup pls on (pl.id = pls.playlist_id)
	WHERE
			p.deleted = false
			AND pl.deleted = false
			AND (b.active IS NULL OR b.active = true)
			AND pl.publicationdate IS NOT NULL
			AND br.template_id IS NOT NULL
			AND br.deleted is false
			AND b.deleted is false
			AND (br.displayStartDate IS NULL OR br.displayStartDate <= current_date) AND (br.displayEndDate IS NULL OR current_date <= br.displayEndDate)
			-- Filter screengroups on the playlist level
			AND ( (( SELECT COUNT( * ) FROM playlist_screengroup WHERE playlist_id = pl.id ) = 0 )
				OR  (( SELECT COUNT( * ) FROM playlist_screengroup WHERE playlist_id = pl.id AND screengroup_id = sgs.id ) > 0))
	ORDER BY
			pl.priority DESC
			, pl.id
			, b.orderofbroadcast ASC
)
-- Filter above playlists based on their playtime schedules
, playlistTime AS (
	SELECT DISTINCT
			p.*,pt.starthour,pt.startminute,pt.endhour,pt.endminute
	FROM
			playlists p
			LEFT JOIN playTime pt ON (p.playlistId = pt.playlist_id)
			LEFT JOIN playtime_days ptd ON (pt.id = ptd.playtime_id)
	WHERE
			p.publication_id = p.publication_id
			AND ptd.days = EXTRACT(DOW FROM current_timestamp)
			AND (
				pt.startDate <= current_date
				OR pt.startDate IS NULL
			)
			AND (
				pt.endDate >= current_date
				OR  pt.endDate IS NULL
			)
			AND (
				SELECT 
					CASE 
						WHEN 
							-- special case we cross the date border
							pt.endhour < pt.starthour
						THEN
						
							CASE 
								WHEN
									-- before midnight = current hour >= start hour
									CAST(to_char(current_timestamp, 'HH24') AS INTEGER) >= pt.starthour
								THEN
									-- we must add a day to enddate
									now() between
									-- today start day
									current_date + (interval '1' HOUR * pt.starthour) + (interval '1 minute' * pt.startminute) 
									and
									-- tomorrow end date
									current_date +  (interval '1' DAY * 1) + (interval '1' HOUR * pt.endhour) + (interval '1 minute' * pt.endminute)
											
								WHEN
									-- after midnight = current hour <= end hour
									CAST(to_char(current_timestamp, 'HH24') AS INTEGER) <= pt.endhour 
								
								THEN
									-- we must deduct a day from startdate
									now() between
									-- yesterday start date
									current_date - (interval '1' DAY * 1) + (interval '1' HOUR * pt.starthour) + (interval '1 minute' * pt.startminute)
									and
									-- today end date
									current_date + (interval '1' HOUR * pt.endhour) + (interval '1 minute' * pt.endminute)
									
								ELSE
									-- any other case must fail 
									False
							END
								
						ELSE
							-- normal case we just compare dates on current day
							now() between
							current_date + (interval '1' HOUR * pt.starthour) + (interval '1 minute' * pt.startminute)
							and
							current_date + (interval '1' HOUR * pt.endhour) + (interval '1 minute' * pt.endminute)

					END
				)
	ORDER BY
			p.priority DESC
			, p.playlistId 
			, p.orderofbroadcast ASC
)
-- Will be replaced by whatever else you want to use this base query for
{1}
