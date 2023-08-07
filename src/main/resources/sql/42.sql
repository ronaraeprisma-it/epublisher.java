--		Copyright 2018, Prisma-IT (www.prisma-it.com)
--		All rights reserved
--		$Id$

-- Create relation table for epublisher user and brightcove folders NS_EPB-1160

CREATE TABLE epublisheruser_allowedBrightcoveFolders
(
  user_id integer NOT NULL,
  allowedBrightcoveFolders integer
);



GRANT ALL ON TABLE epublisheruser_allowedBrightcoveFolders TO epublisher;
GRANT SELECT ON TABLE epublisheruser_allowedBrightcoveFolders TO dashboard;
GRANT SELECT ON TABLE epublisheruser_allowedBrightcoveFolders TO development;

ALTER TABLE epublisheruser_allowedBrightcoveFolders
  ADD CONSTRAINT fk_user
  FOREIGN KEY(user_id)
  REFERENCES  epublisheruser(id);