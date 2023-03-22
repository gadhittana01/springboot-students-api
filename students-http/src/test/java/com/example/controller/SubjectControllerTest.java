package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.models.Subject;
import com.example.models.Teacher;
import com.example.services.SubjectService;

@SpringBootTest
public class SubjectControllerTest {
    @Mock
    private SubjectService subjectService;

    @Test
    void testGetAllSubject(){
        List<Subject> want = new ArrayList<Subject>(){
            {
                add(new Subject(1L, "Computer Science", null, new Teacher(1L, "boba")));
            }
        };
        BDDMockito.given(subjectService.getAllSubject())
            .willReturn(want);
        SubjectController mock = new SubjectController(subjectService);
        Assertions.assertThat(mock.getSubjects()).isEqualTo(want);
    }
    
    @Test
    void testCreateSubject(){
        Subject want = new Subject(1L, "Computer Science", null, new Teacher(1L, "boba"));
        Subject request = new Subject(1L, "Computer Science", null, new Teacher(1L, "boba"));
        BDDMockito.given(subjectService.createSubject(request))
            .willReturn(want);
        SubjectController mock = new SubjectController(subjectService);
        Assertions.assertThat(mock.createSubject(request)).isEqualTo(want);
    }
    
    @Test
    void testEnrollStudentToSubject(){
        // want
        Subject want = new Subject(1L, "Computer Science", null, new Teacher(1L, "boba"));

        // request
        Long subjectID = 1L;
        Long studentID = 1L;

        // condition
        BDDMockito.given(subjectService.enrollStudentToSubject(subjectID, studentID))
            .willReturn(want);
        SubjectController mock = new SubjectController(subjectService);
        Assertions.assertThat(mock.enrollStudentToSubject(subjectID, studentID)).isEqualTo(want);
    }
}
