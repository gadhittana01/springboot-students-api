package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Student;
import com.example.models.Subject;
import com.example.models.Teacher;
import com.example.repositories.StudentRepository;
import com.example.repositories.SubjectRepository;
import com.example.repositories.TeacherRepository;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }
    
    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }

    public Subject createSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    public Subject enrollStudentToSubject(Long subjectID, Long studentID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Student student = studentRepository.findById(studentID).get();
        subject.enrollStudent(student);
        return subjectRepository.save(subject);
    }

    public Subject enrollTeacherToSubject(Long subjectID, Long teacherID){
        Subject subject = subjectRepository.findById(subjectID).get();
        Teacher teacher = teacherRepository.findById(teacherID).get();
        subject.enrollTeacher(teacher);
        return subjectRepository.save(subject);
    }
}
