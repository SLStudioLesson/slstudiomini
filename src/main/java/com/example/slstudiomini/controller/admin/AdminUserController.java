package com.example.slstudiomini.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.slstudiomini.model.User;
import com.example.slstudiomini.service.UserService;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model){
        List<User> users = userService.findEnabledStudent();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("user", new User());
        return "admin/user-add";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "admin/user-add";
        }
        userService.addEnableStudentAndHashPassword(user);
        return "redirect:/admin/users";
    }
}
