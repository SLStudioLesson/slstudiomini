package com.example.slstudiomini.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.model.Course;
import com.example.slstudiomini.model.Lesson;
import com.example.slstudiomini.repository.LessonRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseService courseService;

    public List<Lesson> findAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson findLessonById(Long id) {
        return lessonRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lesson Not Found With id= " + id));
    }

    @Transactional
    public Lesson save(Lesson lesson) {
        lesson.setCreatedAt(LocalDateTime.now());
        return lessonRepository.save(lesson);
    }

    @Transactional
    public Lesson update(Lesson updateLesson) {
        Lesson lesson = findLessonById(updateLesson.getId());

        lesson.setUpdatedAt(LocalDateTime.now());
        lesson.setName(updateLesson.getName());
        lesson.setContent(updateLesson.getContent());
        lesson.setDescription(updateLesson.getDescription());
        lesson.setUpdatedAt(LocalDateTime.now());

        Course course = courseService.findCourseById(updateLesson.getCourse().getId());
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }
}