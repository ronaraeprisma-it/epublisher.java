select updateAllSchemaWithGivenQuery('

ALTER TABLE broadcastwrapper
	ADD COLUMN deleted boolean;
	
ALTER TABLE broadcastwrapper
	ADD COLUMN modified boolean;

ALTER TABLE broadcastwrapper
	ADD COLUMN added boolean;

ALTER TABLE broadcastwrapper
	ADD COLUMN broadcast_parentid integer REFERENCES broadcast(id);

ALTER TABLE playlist
	ADD COLUMN orderDifferentThanPublished boolean;

-- script to update old broadcastwrapper values that were cloned
UPDATE broadcastwrapper bw
SET    broadcast_parentid = b.parentid
FROM   broadcast b
WHERE  bw.broadcast_id = b.id
AND    bw.broadcast_parentid IS DISTINCT FROM b.parentid;

');
