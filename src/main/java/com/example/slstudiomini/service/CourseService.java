package com.example.slstudiomini.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.model.Course;
import com.example.slstudiomini.repository.CourseRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllCources(){
        return courseRepository.findAll();
    }

    public Course findCourceById(Long id){
        return courseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Lesson Not Found With id= " + id));
    }

    @Transactional
    public Course save(Course course){
        if ( course != null ) {
            course.setCreatedAt(LocalDateTime.now());
            courseRepository.save(course);
        }
        return course;
    }

    @Transactional
    public Course update(Course updateCourse){
        Course course = findCourceById(updateCourse.getId());
        course.setName(updateCourse.getName());
        course.setDescription(updateCourse.getDescription());
        course.setUpdatedAt(LocalDateTime.now());

        return courseRepository.save(course);
    }


    @Transactional
    public void delete(Course deletedCourse){
        Course course = findCourceById(deletedCourse.getId());
        // コースの削除はdeletedAtに日付を入れる
        course.setDeletedAt(LocalDateTime.now());
        course.getLessons().forEach(lesson -> lesson.setDeletedAt(LocalDateTime.now()));
        courseRepository.save(course);
    }


}
