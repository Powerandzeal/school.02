package ru.hogwarts.school02.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school02.model.Faculty;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    Map <Long, Faculty> faculties = new HashMap<>();
    private long id;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++id);
        faculties.put(id,faculty);
        return faculty;
    }

    public Faculty updateFaculty(Faculty faculty) {
        faculties.put(faculty.getId(),faculty);
        return faculty;
    }

    public Faculty getFaculty(Long id) {
        return faculties.get(id);
    }

    public Faculty deleteFaculty(Long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultyOnTheColor(String color) {
        return faculties.values().stream().filter(s->s.getColor().equals(color)).collect(Collectors.toList());
    }
}
