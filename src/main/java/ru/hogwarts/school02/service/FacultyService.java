package ru.hogwarts.school02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.repositories.FacultyRepository;


import java.util.Collection;

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


    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
    public Faculty findFacultyOnTheColorOrName(String color,String name) {
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color,name);
    }

    public Faculty findByStudentName(String name) {
       return  facultyRepository.findByStudent_Name(name);
    }
}
