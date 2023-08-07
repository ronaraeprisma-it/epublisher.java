--		Copyright 2017, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Add some missing index to improve the speed of retrieving full editions of newsletters
CREATE INDEX IF NOT EXISTS epublishermedia_attachedURLS_id_idx ON EPublisherMedia (attachedURLS_id);
CREATE INDEX IF NOT EXISTS epublishermedia_attachedDocuments_id_idx ON EPublisherMedia (attachedDocuments_id);
CREATE INDEX IF NOT EXISTS article_relevantarticles_id_idx ON article (relevantarticles_id);
