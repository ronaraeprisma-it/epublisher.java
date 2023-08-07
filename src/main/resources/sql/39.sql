--		Copyright 2017, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add the images for the previews of the narrowcasting templates.
UPDATE templatenarrowcasting
SET previewimage_id = (
  SELECT id 
  FROM epublishermedia 
  WHERE caption = 'Preview image for the ' || templatenarrowcasting.name || ' narrowcasting template.'
);
