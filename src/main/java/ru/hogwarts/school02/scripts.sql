SELECT * FROM student WHERE age < 30 AND AGE > 20;
SELECT name FROM student;
SELECT name FROM student WHERE name LIKE '%o%';
SELECT * FROM student where age< student.id;
SELECT * FROM student ORDER BY age;

SELECT * FROM student,faculty WHERE student.faculty_id = faculty.id
                                AND faculty.color = 'Red';

SELECT * FROM student,faculty WHERE student.faculty_id = faculty.id
                                AND student.name = 'Harry Potter'