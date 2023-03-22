package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.models.Teacher;
import com.example.repositories.TeacherRepository;

@SpringBootTest
public class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;

    @Test
    void testSuccessGetTeachers(){
        List<Teacher> want = new ArrayList<Teacher>(){
            {
                add(new Teacher(1L, "boba"));
            }
        };
        BDDMockito.given(teacherRepository.findAll())
            .willReturn(want);
        TeacherService mock = new TeacherService(teacherRepository);
        Assertions.assertThat(mock.getTeachers()).isEqualTo(want);
    }

    @Test
    void testCreateTeacher(){
        Teacher want = new Teacher(1L, "boba");
        Teacher request = new Teacher(1L, "boba");
        BDDMockito.given(teacherRepository.save(request))
            .willReturn(want);
            TeacherService mock = new TeacherService(teacherRepository);
        Assertions.assertThat(mock.createTeacher(request)).isEqualTo(want);
    }
}
