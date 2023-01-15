package ru.hogwarts.school1.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school1.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lasrid = 0;

    public Student createStudent(Student student) {
        student.setId(++lasrid);
        students.put(lasrid, student);
        return student;
    }

    public Student updateStudent(Student student) {
        students.put(student.getId(), student);
        students.get(lasrid);
        return student;
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> getSameAgeStudent(int age) {
        Collection<Student> stud =
                students.
                        values().
                        stream().
                        filter(s -> s.getAge() == age).
                        collect(Collectors.toSet());
        return stud;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

}
