// Testing Pipeline
/**
 * HomeController is a Spring MVC controller that handles HTTP GET requests for the home page.
 */
package com.gcu.cst323.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController class handles the root URL ("/") and returns the home page
 * view.
 */
@Controller
public class HomeController {

	/**
	 * Handles HTTP GET requests for the root URL ("/").
	 * 
	 * @param model the Model object used to pass attributes to the view
	 * @return the name of the view to be rendered ("/home/index")
	 */
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home"); // Add a title attribute to the model
		model.addAttribute("message", "Welcome to the CST-323 Home Page!"); // Add a message attribute to the model
		return "/home/index";
	}
}
