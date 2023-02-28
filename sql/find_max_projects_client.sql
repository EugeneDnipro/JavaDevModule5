SELECT *
    FROM (SELECT name, count(name) AS project_count
        FROM (SELECT name, project.client_id
            FROM client
            JOIN project
            ON client.id = project.client_id)
        GROUP BY name)
    WHERE project_count = (SELECT MAX(project_count)
        FROM (SELECT name, count(name) AS project_count
            FROM (SELECT name, project.client_id
                FROM client
                JOIN project
                ON client.id = project.client_id)
            GROUP BY name));