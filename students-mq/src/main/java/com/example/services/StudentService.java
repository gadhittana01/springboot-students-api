package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Student;
import com.example.repositories.StudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    
    public List<Student> createStudents(List<Student> students){
        ObjectMapper mapper = new ObjectMapper();
        List<Student> std = mapper.convertValue(
            students, 
            new TypeReference<List<Student>>(){}
        );
        return studentRepository.saveAll(std);
    }
    
}
