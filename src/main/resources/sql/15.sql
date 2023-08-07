ALTER TABLE articlewrapper ALTER COLUMN urgent SET DEFAULT false;
UPDATE articlewrapper SET urgent = FALSE where urgent IS NULL;
ALTER TABLE articlewrapper ALTER COLUMN urgent SET NOT NULL;
