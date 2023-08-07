-- Add maxplaylistpriority column to publication table and put them on 5 for existing narrowcasting publications

ALTER TABLE publication ADD COLUMN maxplaylistpriority integer;

UPDATE publication
SET maxplaylistpriority = 5
WHERE dtype = 'PublicationNarrowcastingNS';

-- Correct playlist priorities

UPDATE playlist
SET priority = 6 - priority;
