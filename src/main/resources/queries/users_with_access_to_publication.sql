SELECT
	u.firstname
	, u.lastname
	, u.middlename
	, u.phonenumber
	, u.email
FROM
	epublisheruser u
	INNER JOIN epublisheruser_publication up on up.epublisheruser_id = u.id
WHERE
	up.availablepublications_id = :publicationid
ORDER BY
	u.firstname
	, u.lastname
