package com.example.slstudiomini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slstudiomini.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}