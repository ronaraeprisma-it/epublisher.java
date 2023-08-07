
---NS_EPB-1208 custom background image for narrowcasting player---


ALTER TABLE screen ADD COLUMN backgroundimage_id integer  NULL

ALTER TABLE screen   ADD CONSTRAINT fk_image_id   FOREIGN KEY (backgroundimage_id)  REFERENCES epublishermedia (id)
  
  
ALTER TABLE screengroup ADD COLUMN backgroundimage_id integer  NULL

ALTER TABLE screengroup   ADD CONSTRAINT fk_image_id   FOREIGN KEY (backgroundimage_id)   REFERENCES epublishermedia (id)