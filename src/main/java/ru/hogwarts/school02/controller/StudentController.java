package ru.hogwarts.school1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school1.model.Student;
import ru.hogwarts.school1.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("{id}")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

//        @GetMapping("/t{id}")
//    public Student getInfoStudent(@PathVariable Long id) {
//        return studentService.getStudent(id);
//    }
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("{age}")
    public Collection<Student> getStudentsSameAge(@PathVariable Integer age) {
        return studentService.getSameAgeStudent(age);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
