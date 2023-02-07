package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school02.model.Avatar;
import ru.hogwarts.school02.model.Student;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findAvatarById(Long avatarId);
    Optional<Avatar> findByStudentId(Long studentId);
    Optional<Avatar> getAllBy();




}
