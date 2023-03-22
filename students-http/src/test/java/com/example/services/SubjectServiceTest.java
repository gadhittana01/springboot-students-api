package com.example.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.models.Address;
import com.example.models.Student;
import com.example.models.Subject;
import com.example.models.Teacher;
import com.example.repositories.StudentRepository;
import com.example.repositories.SubjectRepository;
import com.example.repositories.TeacherRepository;

@SpringBootTest
public class SubjectServiceTest {
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TeacherRepository teacherRepository;


    @Test
    void testGetAllSubject(){
        Set<Student> enrolledStudents = new HashSet<Student>(){{
            add(new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek")));
        }};

        List<Subject> want = new ArrayList<Subject>(){
            {
                add(new Subject(1L, "Computer Science", enrolledStudents, new Teacher(1L, "boba")));
            }
        };
        BDDMockito.given(subjectRepository.findAll())
        .willReturn(want);
        SubjectService mock = new SubjectService(subjectRepository, studentRepository, teacherRepository);
        Assertions.assertThat(mock.getAllSubject()).isEqualTo(want);
    }
    
    @Test
    void testCreateSubject(){
        Set<Student> enrolledStudents = new HashSet<Student>(){{
            add(new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek")));
        }};

        Subject want = new Subject(1L, "Computer Science", enrolledStudents, new Teacher(1L, "boba"));
        Subject request = new Subject(1L, "Computer Science", enrolledStudents, new Teacher(1L, "boba"));
        BDDMockito.given(subjectRepository.save(request))
            .willReturn(want);
        SubjectService mock = new SubjectService(subjectRepository, studentRepository, teacherRepository);
        Assertions.assertThat(mock.createSubject(request)).isEqualTo(want);
    }

    @Test
    void testEnrollStudentToSubject(){
        // want
        Set<Student> enrolledStudents = new HashSet<Student>(){{
            add(new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek")));
        }};
        Optional<Student> student = Optional.of(new Student(2L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek")));
        Optional<Subject> subject = Optional.of(new Subject(1L, "Computer Science", enrolledStudents, new Teacher(1L, "boba")));
        Subject want = new Subject(1L, "Computer Science", enrolledStudents, new Teacher(1L, "boba"));

        // request
        Long subjectID = 1L;
        Long studentID = 1L;

        // condition
        BDDMockito.given(subjectRepository.findById(subjectID))
            .willReturn(subject);
        BDDMockito.given(studentRepository.findById(studentID))
            .willReturn(student);
        BDDMockito.given(subjectRepository.save(subject.get()))
            .willReturn(want);
        SubjectService mock = new SubjectService(subjectRepository, studentRepository, teacherRepository);
        Assertions.assertThat(mock.enrollStudentToSubject(subjectID, studentID)).isEqualTo(want);
    }

}
