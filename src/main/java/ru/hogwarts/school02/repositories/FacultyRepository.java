package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school02.model.Faculty;

import javax.swing.text.Position;
import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
     Faculty findByColorIgnoreCaseOrNameIgnoreCase(String color, String name);

   Faculty findByStudent_Name(String name);
}