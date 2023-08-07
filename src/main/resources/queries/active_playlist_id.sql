SELECT DISTINCT ptl.playlistId, ptl.publicationdate 
FROM
	playlistTime ptl
WHERE ptl.priority = (select max(pt.priority) from playlistTime pt)
ORDER BY
	ptl.playlistId ,
	ptl.publicationdate  desc
