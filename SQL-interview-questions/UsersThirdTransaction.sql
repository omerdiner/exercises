SELECT user_id, spend, transaction_date
FROM (
    SELECT  *,
        ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY transaction_date) AS rnk
    FROM transactions
) AS ranked_transactions
WHERE rnk = 3;