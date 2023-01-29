package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
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



}
