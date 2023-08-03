SELECT DISTINCT candidate_id FROM candidates
WHERE skill IN('Python','Tableau','PostgreSQL')
GROUP BY candidate_id
HAVING COUNT(*)=3;