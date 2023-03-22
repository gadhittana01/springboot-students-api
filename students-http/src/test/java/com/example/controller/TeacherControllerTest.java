package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.models.Teacher;
import com.example.services.TeacherService;

@SpringBootTest
public class TeacherControllerTest {
    @Mock
    private TeacherService teacherService;

    @Test
    void testGetTeachers(){
        List<Teacher> want = new ArrayList<Teacher>(){
            {
                add(new Teacher(1L, "boba"));
            }
        };
        BDDMockito.given(teacherService.getTeachers())
            .willReturn(want);
        TeacherController mock = new TeacherController(teacherService);
        Assertions.assertThat(mock.getTeachers()).isEqualTo(want);
    }
    
    @Test
    void testCreateTeacher(){
        Teacher want = new Teacher(1L, "boba");
        Teacher request = new Teacher(1L, "boba");
        BDDMockito.given(teacherService.createTeacher(request))
            .willReturn(want);
        TeacherController mock = new TeacherController(teacherService);
        Assertions.assertThat(mock.createTeacher(request)).isEqualTo(want);
    }
}
