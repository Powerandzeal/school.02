package ru.hogwarts.school02.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.model.Student;
import ru.hogwarts.school02.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository studentRepository;



    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("create student");
        return studentRepository.save(student);


    }

    public Student updateStudent(Student student) {
        logger.info("update student");
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        logger.info("get student by id {}",id);
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(long id) {
        logger.info("delete student by id {}",id);
        studentRepository.deleteById(id);
    }
//public Collection<Student> getSameAgeStudent(int age) {
//    return students.
//            values()
//            .stream()
//            .filter(student -> student.getAge()==age)
//            .collect(Collectors.toList());
//
//}

    public Collection<Student> getAllStudents() {
        logger.info("get all student");
        return studentRepository.findAll();
    }

    public Collection<Student> findByName(String name) {
        logger.info("find student by name {}",name);
        return studentRepository.findByName(name);

    }

    public Collection<Student> findByAge(Integer max) {
        logger.info("find student by age max {}",max);
        return studentRepository.findByAge(max);
    }

    public Collection<Student> findByAgeBetween(Integer minAge, Integer maxAge) {
        logger.info("find students between min and max age {}{}",minAge,maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> findByFacultyName(String name) {
        logger.info("find students by faculty name {}",name);
        return studentRepository.findByFaculty_NameIgnoreCase(name);
    }

    public int getCountStudents() {
        logger.info("get amount students");
        return studentRepository.getAmountStudents();
    }

    public int getAverageAgeByStudents() {
        logger.info("get average age of students");
        return studentRepository.getAverageAgeStudents();
    }

    public Collection<Student> getLastFiveStudents() {
        logger.info("get last five  students");
        return studentRepository.getLastFiveStudents();
    }

}
