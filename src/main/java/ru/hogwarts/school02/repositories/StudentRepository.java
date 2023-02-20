package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.model.Student;

import javax.swing.text.Position;
import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAge (int age);
    Collection<Student> findByAgeBetween (int minAge,int maxAge);
    Collection<Student> findByName(String name);
    Collection<Student> findByFaculty_Color(String color);
    Collection<Student> findByFaculty_NameIgnoreCase(String name);
    Collection<Student> findByFaculty_NameIgnoreCaseOrFaculty_ColorIgnoreCase(String name,String color);


    @Query(value = "select count(*) from student",nativeQuery = true)
    int getAmountStudents();
    @Query(value = "SELECT avg(age) from student",nativeQuery = true)
    int getAverageAgeStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5",nativeQuery = true)
    Collection<Student> getLastFiveStudents();




}
