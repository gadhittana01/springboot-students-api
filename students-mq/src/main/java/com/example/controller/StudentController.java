package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.models.Student;
import com.example.services.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @KafkaListener(
        topics = "kafka_add_students",
        groupId = "channelStudent",
        errorHandler = "handler"
    )
    public void consumer(List<Student> students){
        log.info(String.format("Json message received %s",students.toString()));
        try {
            studentService.createStudents(students);
        } catch (Exception e) {
            System.out.println("error : "+e);
        };
    }
}
