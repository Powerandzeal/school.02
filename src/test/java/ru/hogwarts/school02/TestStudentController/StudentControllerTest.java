package ru.hogwarts.school02.TestStudentController;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school02.controller.StudentController;
import ru.hogwarts.school02.model.Faculty;
import ru.hogwarts.school02.model.Student;
import ru.hogwarts.school02.repositories.AvatarRepository;
import ru.hogwarts.school02.repositories.StudentRepository;
import ru.hogwarts.school02.service.AvatarService;
import ru.hogwarts.school02.service.FacultyService;
import ru.hogwarts.school02.service.StudentService;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private FacultyService facultyService;

    @SpyBean
    private StudentService studentService;

    @SpyBean
    private AvatarService avatarService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void createStudentTest() throws Exception {
        final String name = "Gosha";
        final int age = 22;
        final long id = 1;
        JSONObject studentObject = new JSONObject();
        studentObject.put("Gosha", 22);

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value(name))
                .andExpect(status().isOk()).andExpect(jsonPath("$.age").value(age));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/" + id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

//    @Test
//    public void testStudents() throws Exception {
//        final String name = "Ivanov Ivan";
//        final int age = 21;
//        final long id = 1;
//
//        Student student = new Student(id, name, age);
//
//        JSONObject studentObject = new JSONObject();
//        studentObject.put("id", id);
//        studentObject.put("name", name);
//        studentObject.put("age", age);
//
//
//        when(studentRepository.save(any(Student.class))).thenReturn(student);
//        when(studentRepository.findAll(any(int.class))).thenReturn(Collections.singleton(student));
//        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/student")
//                        .content(studentObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.age").value(age));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put("/student")
//                        .content(studentObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.age").value(age));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/student/" + id)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value(name))
//                .andExpect(jsonPath("$.age").value(age));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/student?age" + age)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(id))
//                .andExpect(jsonPath("$[0].name").value(name))
//                .andExpect(jsonPath("$[0].age").value(age));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .delete("/student/" + id)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void editStudent() {
//    }
//
//    @Test
//    void getStudentInfo() {
//    }
//
//    @Test
//    void testApi() {
//    }
//
//    @Test
//    void getAllStudents() {
//    }
//
//    @Test
//    void findByAge() {
//    }
//
//    @Test
//    void findByAgeBetween() {
//    }
//
//    @Test
//    void findByFacyltyName() {
//    }
}