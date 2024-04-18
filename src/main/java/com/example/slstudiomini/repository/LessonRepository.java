package com.example.slstudiomini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.slstudiomini.model.Lesson;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    
}