package ru.hogwarts.school02.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school02.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student updateStudent(Student student) {
        students.put(student.getId(), student);
        students.get(lastId);
        return student;
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

//    public Collection<Student> getSameAgeStudent(int age) {
//        ArrayList<Student> result = new ArrayList();
//        for (Student student : students.values()) {
//            if (student.getAge() == age) {
//                result.add(student);
//            }
//        }
//        return result;
//
//    }
public Collection<Student> getSameAgeStudent(int age) {

    return students.values().stream().filter(student -> student.getAge()==age).collect(Collectors.toList());

}

    public Collection<Student> getAllStudents() {
        return students.values();
    }

}
