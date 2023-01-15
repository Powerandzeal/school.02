package ru.hogwarts.school02.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.service.FacultyService;

import java.util.Collection;

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
    public Faculty deleteFaculty (@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty getInfoFaculty(@PathVariable Long id) {
        return facultyService.getFaculty(id);
    }
    @GetMapping
    public Collection<Faculty> getFacultyForColor(@RequestParam String color){
        return facultyService.getFacultyOnTheColor(color);
    }
}
