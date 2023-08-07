ALTER TABLE articlewrapper ADD COLUMN urgent boolean;

UPDATE articlewrapper SET urgent = false;

ALTER TABLE publication ADD COLUMN urgentarticlesenabled boolean;

UPDATE publication SET urgentarticlesenabled = false;