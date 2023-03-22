package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>{
    
}
