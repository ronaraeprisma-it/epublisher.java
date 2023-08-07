select  updateAllSchemaWithGivenQuery('
ALTER TABLE train ADD COLUMN externaldisplaycodes varchar;

-- train equipment numbers table
CREATE TABLE train_rollingstocktypes(train_id INTEGER NOT NULL, rollingstocktypes character varying(255));
ALTER TABLE train_rollingstocktypes ADD CONSTRAINT fk_train_rollingstocktypes_train_id  FOREIGN KEY (train_id)  REFERENCES  train(id);

GRANT ALL ON TABLE train_rollingstocktypes TO epublisher;
GRANT SELECT, REFERENCES ON TABLE train_rollingstocktypes TO dashboard;
')
