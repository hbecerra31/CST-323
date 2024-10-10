/**
 * 
 */
package com.gcu.cst323.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 */
@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home");
		model.addAttribute("message", "Welcome to the CST-323 Home Page!");
		return "/home/index";
	}
}
