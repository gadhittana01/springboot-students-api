package com.example.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
@Table
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<Student>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    public Subject(Long id, String name, Set<Student> enrolledStudents, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.enrolledStudents = enrolledStudents;
        this.teacher = teacher;
    }

    public Subject() {
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void enrollStudent(Student student){
        enrolledStudents.add(student);
    }

    public void enrollTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Set<Student> getEnrolledStudents() {
        return this.enrolledStudents;
    }

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}