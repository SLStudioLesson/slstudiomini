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
import com.example.slstudiomini.service.CourseService;

@Controller
@RequestMapping("/admin/courses")
public class AdminCourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String index(Model model) {
        List<Course> courses = courseService.findAllCourses();
        model.addAttribute("courses", courses);
        return "admin/course-list.html";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("course", new Course());
        return "admin/course-add.html";
    }

    @PostMapping("/add")
    public String add(Course course) {
        courseService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findCourseById(id);
        model.addAttribute("course", course);
        return "admin/course-edit";
    }

    @PostMapping("/edit")
    public String edit(Course course) {
        courseService.update(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findCourseById(id);
        model.addAttribute("course", course);
        return "/admin/course-delete";
    }

    @PostMapping("/delete")
    public String delete(Course course) {
        courseService.delete(course);
        return "redirect:/admin/courses";
    }
}