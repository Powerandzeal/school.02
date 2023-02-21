package ru.hogwarts.school02.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school02.service.StudentService;
import ru.hogwarts.school02.model.Student;

import java.util.Collection;
import java.util.stream.Stream;

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
    public ResponseEntity removeStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/studentgreeting")
    public String testApi() {
        return "hello student";
    }
    @GetMapping("/count")
    public int countStudents() {
        return studentService.getCountStudents();
    }
    @GetMapping("/averageAge")
    public int averageAgeOfStudents() {
        return studentService.getAverageAgeByStudents();
    }
    @GetMapping("/lastFiveStudents")
    public Collection<Student> showLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

//    @GetMapping
//    public ResponseEntity <Collection <Student>> findStudents(@RequestParam(required = false) int age) {
//        if (age > 0) {
//            return ResponseEntity.ok(studentService.getSameAgeStudent(age));
//        }
//        return ResponseEntity.ok(Collections.emptyList());
//    }

    @GetMapping
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/age")
    public Collection<Student> findByAge(@RequestParam Integer age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/ageBetween")
    public Collection<Student> findByAgeBetween(@RequestParam Integer minAge,
                                                @RequestParam Integer maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

        @GetMapping("/findStudentsByFacyltyName")
    public Collection<Student> findByFacyltyName(@RequestParam String name) {
            return studentService.findByFacultyName(name);
        }

    @GetMapping("getStudentWhereNameStartsWithAlphA")
    public Stream<String> getStudentWhereNameStartsWithAlphA() {
        return studentService.getStudentWhereNameStartsWithAlphA();
    }
    @GetMapping("getStudentAverageAge")
    public double getStudentAverageAge () {
        return studentService.getAverageAge();
    }
    @GetMapping("Phomula")
    public long phormula () {
        return studentService.phormula1();
    }
    @GetMapping("Phomula2")
    public long phormula2 () {
        return studentService.phormula2();
    }
//    }

    @GetMapping("getListNameStudents")
    public void getListName () {
        studentService.getListNameStudentsFromThread();
    }
    @GetMapping("getListNameStudentsSynchronized")
    public void getListNameSynchronized () {
        studentService.getListNameStudentsSynchronizedThread();
    }
//    }
//    @GetMapping("/findStudentsByFacyltyColor")
//    public Collection<Student> findByFacyltyColor(@RequestParam String color
//    ) {
//        return studentService.findByFacultiesColor(color);
//    }
//    @GetMapping("/findByFaculty_NameIgnoreCaseOrFaculty_ColorIgnoreCase")
//    public Collection<Student> findByFacyltyColor(@RequestParam (required = false)String color,
//                                                  @RequestParam (required = false)String name) {
//        return studentService.findByFaculty_NameIgnoreCaseOrFaculty_ColorIgnoreCase(name,color);

}
