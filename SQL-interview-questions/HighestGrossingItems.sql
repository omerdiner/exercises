WITH ranks AS
(SELECT category,product,
SUM(spend) as total_spend,
RANK() OVER( PARTITION BY category ORDER BY SUM(spend)  desc
) as rnk FROM product_spend WHERE EXTRACT(YEAR FROM transaction_date )=2022
GROUP BY category,product
)

SELECT category,product,total_spend FROM ranks 
WHERE rnk<=2 
ORDER BY category,rnk;