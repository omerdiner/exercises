SELECT user_id FROM emails
JOIN texts ON texts.email_id=emails.email_id
WHERE signup_action ='Confirmed' 
AND date(action_date)-date(signup_date)=1
GROUP BY user_id;