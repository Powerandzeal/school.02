package ru.hogwarts.school02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.repositories.FacultyRepository;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);

    }

    public Faculty getFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public void deleteFaculty(long id) {
         facultyRepository.deleteById(id);
    }

//    public Collection<Faculty> getFacultyOnTheColor(String color) {
//        return faculties.values().stream().filter(s->s.getColor().equals(color)).collect(Collectors.toList());
//    }
}
