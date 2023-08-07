-- Add apiid column to publication table. Needed for NS Mobile

ALTER TABLE publication ADD COLUMN "apiid" integer;