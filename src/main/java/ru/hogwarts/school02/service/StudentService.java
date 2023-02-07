package ru.hogwarts.school02.service;

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

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(long id) {
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
        return studentRepository.findAll();
    }

    public Collection<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Collection<Student> findByAge(Integer max) {
        return studentRepository.findByAge(max);
    }

    public Collection<Student> findByAgeBetween(Integer minAge, Integer maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Collection<Student> findByFacultyName(String name) {
        return studentRepository.findByFaculty_NameIgnoreCase(name);
    }

    public int getCountStudents() {
        return studentRepository.getAmountStudents();
    }

    public int getAverageAgeByStudents() {
        return studentRepository.getAverageAgeStudents();
    }

    public Collection<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }

}
