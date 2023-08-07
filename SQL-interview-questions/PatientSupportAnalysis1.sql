SELECT COUNT(*) AS member_count 
FROM
(SELECT policy_holder_id,count(distinct case_id) as member_count 
FROM callers
GROUP BY policy_holder_id
HAVING COUNT(distinct case_id) >=3) as sub ;