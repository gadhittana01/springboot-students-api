package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.models.Address;
import com.example.models.Student;
import com.example.services.StudentService;

@SpringBootTest
public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    

    @Test
    void testGetStudents(){
        List<Student> want = new ArrayList<Student>(){
            {
                add(new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek")));
                add(new Student(2L,"giri.adhittana@gmail.com", new Address(2L, "Thamrin")));
            }
        };
        BDDMockito.given(studentService.getAllStudent())
            .willReturn(want);
        StudentController mock = new StudentController(studentService);
        Assertions.assertThat(mock.getStudents()).isEqualTo(want);
    }
    
    @Test
    void testCreateStudent(){
        Student want = new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek"));
        Student request = new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek"));
        BDDMockito.given(studentService.createStudent(request))
            .willReturn(want);
        StudentController mock = new StudentController(studentService);
        Assertions.assertThat(mock.createStudent(request)).isEqualTo(want);
    }
}
