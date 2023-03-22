package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Student;
import com.example.services.StudentService;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentServices;
    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    public StudentController(StudentService studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public List<Student> getStudents(){
        System.out.println("URL : "+url);
        return studentServices.getAllStudent();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentServices.createStudent(student);
    }

    @PostMapping("/list")
    public String createStudentList(@RequestBody List<Student> students){
        return studentServices.createStudentList(students);
    }
}
