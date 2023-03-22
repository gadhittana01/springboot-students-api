package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.Teacher;
import com.example.repositories.TeacherRepository;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
}
