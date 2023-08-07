ALTER TABLE contentblock ADD COLUMN enabled boolean NULL;
UPDATE contentblock SET enabled = true;
ALTER TABLE contentblock ALTER COLUMN enabled SET NOT NULL;
