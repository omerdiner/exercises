WITH laptop_reviews_cte AS (
    SELECT COUNT(*) as laptop_reviews
    FROM viewership
    WHERE device_type = 'laptop'
)
SELECT
    laptop_reviews,
    (SELECT COUNT(*) FROM viewership) - laptop_reviews as mobile_views
FROM laptop_reviews_cte;