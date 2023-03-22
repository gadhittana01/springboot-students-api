package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Subject;
import com.example.services.SubjectService;

@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getSubjects(){
        return subjectService.getAllSubject();
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject){
        return subjectService.createSubject(subject);
    }

    @PutMapping("/{subjectID}/students/{studentID}")
    public Subject enrollStudentToSubject(@PathVariable Long subjectID, @PathVariable Long studentID){
        return subjectService.enrollStudentToSubject(subjectID, studentID);
    }

    @PutMapping("/{subjectID}/teacher/{teacherID}")
    public  Subject enrollTeacherToSubject(@PathVariable Long subjectID, @PathVariable Long teacherID){
        return subjectService.enrollTeacherToSubject(subjectID, teacherID);
    }
}
