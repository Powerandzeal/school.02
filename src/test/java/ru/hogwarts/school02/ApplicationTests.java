package ru.hogwarts.school02;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school02.controller.StudentController;
import ru.hogwarts.school02.model.Student;
import ru.hogwarts.school02.repositories.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getStudent() {

    }


//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private StudentRepository studentRepository;



//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private StudentController studentController;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Test
//    void contextLoads() throws Exception {
//        Assertions.assertThat(studentController).isNotNull();
//    }
//
//    @Test
//    public void testDefaultMessage() throws Exception {
//        Assertions
//                .assertThat(this
//                        .testRestTemplate
//                        .getForObject("http://localhost:" + port + "/student/studentgreeting", String.class))
//                .contains("hello student");
//    }
//
//    @Test
//    public void testGetStudent() throws Exception {
//        Assertions
//                .assertThat(this
//                        .testRestTemplate
//                        .getForObject("http://localhost:" + port + "/student", String.class))
//                .isNotNull();
//    }
//
//    @Test
//    public void testGetAge() throws Exception {
//        Assertions
//                .assertThat(this
//                        .testRestTemplate
//                        .getForObject("http://localhost:" + port + "/student/age", String.class))
//                .isNotNull();
//    }
//
//    @Test
//    public void testGetAgeBetween() throws Exception {
//        Assertions
//                .assertThat(this
//                        .testRestTemplate
//                        .getForObject("http://localhost:" + port + "/student/ageBetween", String.class))
//                .isNotNull();
//    }
//
//    @Test
//    public void testFindStudent() throws Exception {
//        Assertions
//                .assertThat(this
//                        .testRestTemplate
//                        .getForObject("http://localhost:" + port + "/student/findStudentsByFacyltyName"
//                                , String.class))
//                .isNotNull();
//    }
//
//    @Test
//    public void testPostStudent() throws Exception {
//        Student student = new Student();
//        student.setName("Vova");
//        student.setAge(12);
//
//        Assertions
//                .assertThat(this
//                        .testRestTemplate
//                        .postForObject("http://localhost:" + port + "/student"
//                                , student, String.class))
//                .isNotNull();
//        long id = student.getId();
//        Assertions
//                .assertThat(this.testRestTemplate.delete("http://localhost:" +
//                        port +"/student/"+id,Student.class));
//    }


}
