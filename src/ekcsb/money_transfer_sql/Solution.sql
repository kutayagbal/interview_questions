SELECT name,
       coalesce(sum(CASE
                        WHEN MONEY>0 THEN MONEY
                    END), 0) deposit,
       coalesce(sum(CASE
                        WHEN MONEY<0 THEN MONEY
                    END), 0) * (-1) witdraw
FROM transfers
GROUP BY name