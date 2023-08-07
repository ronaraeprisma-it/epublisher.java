ALTER TABLE "epublishermedia" ADD COLUMN generated boolean NULL;
UPDATE epublishermedia SET generated = false;
