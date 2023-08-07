SELECT DISTINCT ON
      (pt.broadcast_id) pt.broadcast_id ,
	cb.name,
	cb.dtype,
	cb.url as videoUrl,
	cb.videoId,
	em.id,
	em.url,
	pt.publicationdate ,
	pt.broadcastChangedDate,
cb.source
	
	
FROM
	playlistTime pt
	LEFT JOIN broadcast_contentblock brcb on (brcb.broadcast_id = pt.broadcast_id)
	LEFT JOIN contentblock cb on (brcb.contentblocks_id = cb.id)
	LEFT JOIN epublishermedia em on (cb.image_id = em.id)
       
ORDER BY
pt.broadcast_id ,
	cb.name,
	cb.dtype,
	cb.url,
	cb.videoId,
	em.id,
	em.url,
	pt.publicationdate desc
