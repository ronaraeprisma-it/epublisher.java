
--For Reason for adding new column - For new playlist publish logic
-- Logic :  A
	-- 			|
	-- Publish->A ---> A(A)
	-- 			  		|
	-- Edit->(A to C)  C(A)
	-- 					|
	-- Publish->	   C(C)(delete A)

ALTER TABLE playlist ADD COLUMN ancestorplaylistid integer  NULL