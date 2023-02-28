SELECT
 CASE WHEN birthday=(SELECT MAX(birthday) FROM worker) THEN 'YOUNGEST'
     WHEN birthday=(SELECT MIN(birthday) FROM worker) THEN 'ELDEST'
 END AS type, name, birthday
 FROM worker WHERE (CASE WHEN birthday=(SELECT MAX(birthday) FROM worker) THEN 'YOUNGEST'
                        WHEN birthday=(SELECT MIN(birthday) FROM worker) THEN 'ELDEST'
                   END) != 'null';