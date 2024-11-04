
package com.gcu.cst323.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gcu.cst323.models.UserModel;
import com.gcu.cst323.services.UserService;

/**
 * UserController is a Spring MVC controller that handles HTTP requests related
 * to user operations.
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Handles HTTP GET requests to show the registration page.
	 *
	 * @param model the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered ("user/register")
	 */
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("title", "Register"); // Set title attribute for the view
		model.addAttribute("message", "Please register"); // Set message attribute for the view
		model.addAttribute("user", new UserModel()); // Create a new UserModel object
		return "user/register";
	}

	/**
	 * Handles HTTP POST requests to register a new user.
	 *
	 * @param user  the UserModel object containing the user data
	 * @param model the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered or a redirect URL
	 */
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

	/**
	 * Handles HTTP GET requests to show the login page.
	 *
	 * @return the name of the view to be rendered ("user/login")
	 */
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("title", "Login"); // Set title attribute for the view
		model.addAttribute("message", "Please login"); // Set message attribute for the view
		return "user/login";
	}

	/**
	 * Handles HTTP POST requests to log in a user. Note: Authentication would
	 * normally be handled by Spring Security.
	 *
	 * @param username the username of the user
	 * @param password the password of the user
	 * @param model    the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered or a redirect URL
	 */
	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
		// Find user by username
		UserModel user = userService.findUserByUsername(username)
				.orElseThrow(() -> new RuntimeException("User not found")); // Throw exception if user not found
		
		// Check password
//		if (!userService.checkPassword(password, user.getPassword())) {
//			model.addAttribute("error", "Invalid credentials");
//			return "user/login";
//		}

		// Set session or redirect to home page after successful login
		model.addAttribute("user", user);
		return "redirect:/tasks"; // Redirect to tasks after successful login
	}
}
