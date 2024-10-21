package com.gcu.cst323.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Show the registration page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "user/register";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/users/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "user/register";
        }
    }

    // Show the login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }

    // Handle login (authentication would normally be handled by Spring Security)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        return "redirect:/tasks"; // Redirect to tasks after successful login
    }
}

