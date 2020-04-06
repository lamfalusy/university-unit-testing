package com.university.whitebox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.university.whitebox.model.User;
import com.university.whitebox.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user, BindingResult result, Model model) {
        service.createUser(user);
        model.addAttribute("users", service.getUsers());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user, BindingResult result, Model model) {
        service.updateUser(user);
        model.addAttribute("users", service.getUsers());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        service.deleteUser(id);
        model.addAttribute("users", service.getUsers());
        return "index";
    }

}
