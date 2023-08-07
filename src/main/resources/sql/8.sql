ALTER TABLE publication ADD COLUMN deleteddatetime timestamp NULL;

UPDATE publication set deleteddatetime = '1970-01-01' where deleted = true;

DROP INDEX IF EXISTS publication_lower_name_idx;

CREATE UNIQUE INDEX publication_lower_name_idx
  ON publication
  USING btree
  (lower(name::text), outputchannel_id, deleted, COALESCE(deleteddatetime, '1971-01-01'::timestamp));
 
CREATE UNIQUE INDEX screen_displayid_idx
   ON screen (displayid ASC NULLS LAST);