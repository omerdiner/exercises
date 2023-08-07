SELECT manufacturer,(COUNT(drug)) as drug_count,(SUM(cogs)-SUM(total_sales)) as total_loss
FROM pharmacy_sales
WHERE cogs>total_sales
GROUP BY manufacturer
ORDER BY total_loss DESC;