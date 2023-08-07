SELECT age_bucket, 
ROUND(100.0*SUM(time_spent) FILTER (WHERE activity_type ='send')/SUM(time_spent),2) as send_perc,
ROUND(100.0*SUM(time_spent) FILTER (WHERE activity_type ='open')/SUM(time_spent),2) as open_perc FROM activities 
JOIN age_breakdown ON age_breakdown.user_id=activities.user_id
WHERE activity_type='open' OR activity_type='send'
GROUP BY age_bucket;