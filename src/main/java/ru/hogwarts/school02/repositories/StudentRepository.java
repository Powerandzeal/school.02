package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school02.model.Student;

import javax.swing.text.Position;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
