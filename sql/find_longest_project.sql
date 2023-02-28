SELECT *
    FROM (SELECT id AS name, DATEDIFF(MONTH, START_DATE, FINISH_DATE) AS month_count
        FROM project
        GROUP BY name)
    WHERE month_count = (SELECT MAX(month_count)
        FROM (SELECT id AS name, DATEDIFF(MONTH, START_DATE, FINISH_DATE) AS month_count
            FROM project
            GROUP BY name));