package com.example.slstudiomini.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstudiomini.model.Course;
import com.example.slstudiomini.model.Lesson;
import com.example.slstudiomini.service.CourseService;
import com.example.slstudiomini.service.LessonService;

@Controller
@RequestMapping("/admin/lessons")
public class AdminLessonController {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public String index(Model model) {
        List<Lesson> lessons = lessonService.findAllLessons();
        model.addAttribute("lessons", lessons);
        return "admin/lesson-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("lesson", new Lesson());
        return "admin/lesson-add";
    }

    @PostMapping("/add")
    public String add(Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/admin/lessons";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        List<Course> courses = courseService.findAllCourses();
        Lesson lesson = lessonService.findLessonById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courses", courses);
        return "admin/lesson-edit";
    }

    @PostMapping("/edit")
    public String edit(Lesson lesson) {
        lessonService.update(lesson);
        return "redirect:/admin/lessons";
    }
}