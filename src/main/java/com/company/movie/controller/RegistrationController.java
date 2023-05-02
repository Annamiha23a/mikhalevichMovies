package com.company.movie.controller;

import com.company.movie.entity.User;
import com.company.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {
	private UserService userService;

	@Autowired
	public RegistrationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute(new User());
		return "registration";
	}

	@PostMapping("/registration")
	public String addUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		userService.save(user);
		return "redirect:/products";
	}
}
