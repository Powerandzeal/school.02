package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school02.model.Faculty;

import javax.swing.text.Position;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
