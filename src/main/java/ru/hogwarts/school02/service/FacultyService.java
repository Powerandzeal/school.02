package ru.hogwarts.school02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.repositories.FacultyRepository;


import java.util.Collection;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("create faculty");
        return facultyRepository.save(faculty);

    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("update faculty");
        return facultyRepository.save(faculty);

    }

    public Faculty getFaculty(long id) {
        logger.info("get faculty{}",id);
        return facultyRepository.findById(id).get();
    }

    public void deleteFaculty(long id) {
        logger.info("delete faculty{}",id);
         facultyRepository.deleteById(id);
    }


    public Collection<Faculty> getAllFaculties() {
        logger.info("gel all faculty");
        return facultyRepository.findAll();
    }
    public Faculty findFacultyOnTheColorOrName(String color,String name) {
        logger.info("finding faculty by color or name");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color,name);
    }

    public Faculty findByStudentName(String name) {
        logger.info("find faculty by nama {}",name);
       return  facultyRepository.findByStudent_Name(name);
    }
}
