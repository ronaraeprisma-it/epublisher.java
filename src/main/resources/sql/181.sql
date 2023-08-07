select updateAllSchemaWithGivenQuery('

ALTER TABLE article ADD COLUMN parentid integer REFERENCES article(id);
ALTER TABLE broadcast ADD COLUMN parentid integer REFERENCES broadcast(id);
ALTER TABLE articlewrapper ADD COLUMN article_parentid integer REFERENCES article(id);

');