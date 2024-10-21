package com.gcu.cst323.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.services.UserService;

@Controller
public class LoginController {

	@Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login/login";  // Return a view that renders the login form
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        // Custom logic to authenticate user
        UserModel user = userService.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Assuming passwords are hashed with BCrypt
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        if (!userService.checkPassword(password, user.getPassword())) {
            model.addAttribute("error", "Invalid credentials");
            return "login/login";
        }

        // Set session or redirect to home page after successful login
        model.addAttribute("user", user);
        return "redirect:/";
    }
}
