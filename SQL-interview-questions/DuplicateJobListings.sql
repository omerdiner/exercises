SELECT COUNT(*)/2 FROM job_listings a 
JOIN job_listings b ON A.company_id=b.company_id
WHERE a.title=b.title AND a.description=b.description AND a.job_id!=b.job_id;