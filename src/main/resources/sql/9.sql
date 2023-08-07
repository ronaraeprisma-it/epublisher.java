CREATE UNIQUE INDEX playlist_lower_name_idx
   ON playlist USING btree (publication_id ASC NULLS LAST, lower(name::text) ASC NULLS LAST)
WHERE	deleted = false
AND		publicationdate IS NULL;

CREATE UNIQUE INDEX playlist_lower_name_published_idx
   ON playlist USING btree (publication_id ASC NULLS LAST, lower(name::text) ASC NULLS LAST)
WHERE	deleted = false
AND		publicationdate IS NOT NULL;