package ru.hogwarts.school02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school02.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findAvatarById (Long avatarId);

}
