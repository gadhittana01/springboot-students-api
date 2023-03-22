package com.example.repositories;

import org.springframework.stereotype.Repository;

import com.example.models.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    
}
