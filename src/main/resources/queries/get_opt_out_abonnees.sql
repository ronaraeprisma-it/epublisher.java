Select SUM(e.ent) FROM (
select count(*) AS ENT
from edition e
inner join edition_profilescdp ep on e.id = ep.edition_id
inner join profile_cdp p on ep.availableprofile_id = p.id
inner join subscribe s on s.email = p.email
where s.unsubscribed = true
and {0}

union

SELECT  ((select sum(e.totalnumberofsentmails) as snt from edition e where {0} ) - count(g_s.subscribers_id)) AS  ENT
FROM subscriptiongroup_subscriber g_s
INNER JOIN subscriptiongroup g ON g.id = g_s.subscriptiongroup_id
INNER JOIN edition_subscriptiongroup e_g ON e_g.subscriptiongroups_id = g_s.subscriptiongroup_id
inner join edition e on e_g.edition_id = e.id
WHERE {0}

) as e
