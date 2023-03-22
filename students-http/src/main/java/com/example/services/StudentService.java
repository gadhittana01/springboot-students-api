package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Student;
import com.example.repositories.KafkaRepository;
import com.example.repositories.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final KafkaRepository<List<Student>> kafkaRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, KafkaRepository<List<Student>> kafkaRepository) {
        this.studentRepository = studentRepository;
        this.kafkaRepository = kafkaRepository;
    }

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    
    public String createStudentList(List<Student> students){
        String topic = "kafka_add_students";
        kafkaRepository.sendMessage(students, topic);
        return "Request sent to topic";
    }
}
