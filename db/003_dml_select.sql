SELECT p.id AS "Идентификатор", p.name AS "Сотрудник", c.name AS "Компания"
FROM company c JOIN person p ON c.id = p.company_id
WHERE c.id <> 5;

SELECT c.name AS "Компания", COUNT(p.id) AS "Количество человек"
FROM company c JOIN person p ON c.id = p.company_id
GROUP BY c.name
HAVING COUNT(p.id) = (
    SELECT MAX(persons) FROM (
        SELECT COUNT(id) AS persons FROM person GROUP BY company_id
    )
)
ORDER BY c.name ASC;