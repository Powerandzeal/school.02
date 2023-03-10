package ru.hogwarts.school02.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.service.FacultyService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getInfoFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public Collection<Faculty> getAllfaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/findColorOrName")
    public Faculty getFacultyForColor(@RequestParam(required = false) String color,
                                                  @RequestParam(required = false) String name) {
        return facultyService.findFacultyOnTheColorOrName(color, name);
    }

    @GetMapping("/findByStudentName")
    public Faculty findByStudentName(@RequestParam String name) {
        return facultyService.findByStudentName(name);
    }
    @GetMapping("/findBiggestNameFaculty")
    public Optional<String> findBiggestNameFaculty1() {
        return facultyService.getLongerNameFaculty1();
    }
}
