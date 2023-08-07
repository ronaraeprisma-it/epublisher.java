-- Script sets relationship for the first automatically generated image in article

-- update children (thumbnails) with parent ids
UPDATE epublishermedia as main
  SET parentimageid = (SELECT id FROM epublishermedia WHERE generated = false AND name='FULL_1024' AND images_id is not null and images_id = main.thumbnails_id order by id LIMIT 1)
  WHERE generated = true and parentimageid is null
    and id < (SELECT id FROM epublishermedia WHERE generated = false AND name='FULL_1024' AND images_id is not null and images_id = main.thumbnails_id order by id LIMIT 1) + 11 
    and id > (SELECT id FROM epublishermedia WHERE generated = false AND name='FULL_1024' AND images_id is not null and images_id = main.thumbnails_id order by id LIMIT 1);

-- update parents (origin) with -1 - signifies it is parent
UPDATE epublishermedia as main
  SET parentimageid = -1
  WHERE id = (SELECT id FROM epublishermedia WHERE generated = false AND name='FULL_1024' AND images_id is not null and images_id = main.images_id order by id LIMIT 1);
