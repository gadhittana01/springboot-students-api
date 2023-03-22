package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.models.Address;
import com.example.models.Student;
import com.example.repositories.KafkaRepository;
import com.example.repositories.StudentRepository;

@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    
    @Mock
    private KafkaRepository<List<Student>> kafkaRepository;

    @Test
    void testSuccessGetAllStudent(){
        List<Student> want = new ArrayList<Student>(){
            {
                add(new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek")));
                add(new Student(2L,"giri.adhittana@gmail.com", new Address(2L, "Thamrin")));
            }
        };
        BDDMockito.given(studentRepository.findAll())
            .willReturn(want);
        StudentService mock = new StudentService(studentRepository, kafkaRepository);
        Assertions.assertThat(mock.getAllStudent()).isEqualTo(want);
    }

    @Test
    void testCreateStudent(){
        Student want = new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek"));
        Student request = new Student(1L,"giri.adhittana@icloud.com", new Address(1L, "Anggrek"));
        BDDMockito.given(studentRepository.save(request))
            .willReturn(want);
        StudentService mock = new StudentService(studentRepository, kafkaRepository);
        Assertions.assertThat(mock.createStudent(request)).isEqualTo(want);
    }
}
