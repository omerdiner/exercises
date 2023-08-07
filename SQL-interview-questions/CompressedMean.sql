SELECT ROUND(1.0*SUM(item_count*order_occurrences)/SUM(order_occurrences),1) AS mean 
FROM items_per_order;