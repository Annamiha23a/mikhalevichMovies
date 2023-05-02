package com.company.movie.controller;

import com.company.movie.entity.User;
import com.company.movie.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
	private final UserServiceImpl userService;

	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String findAllUsers(Model model) {
		List<User> users = userService.findAllUsers();
		users.forEach(System.out::println);
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/user/{id}")
	public String findUser(@PathVariable(value = "id") int id, Model model) {
		User user = userService.findUserById(id);
		List<User> users = new ArrayList<>();
		users.add(user);
		model.addAttribute("user", users);
		return "user-details";
	}

	@PostMapping("/user/{id}/remove")
	public String removeUser(@PathVariable(value = "id") int id, Model model) {
		userService.removeUser(id);
		return "users";
	}

	@PostMapping("user/{id}/ban")
	public String banUser(@PathVariable(value = "id")int id, Model model){
		userService.banUser(id);
		return"redirect:/users";
	}
	@GetMapping("/user/{id}/update")
	public String updateUser(@PathVariable(value = "id") int id, Model model) {
		User user = userService.findUserById(id);
		List<User> users = new ArrayList<>();//? почему тут список? Так было в других методах
		users.add(user);
		model.addAttribute("user",users);
		return "updateUser";
	}
	@PostMapping("/user/{id}/updateU")
	public String updateU(@PathVariable(value = "id") int id, @RequestParam(value="firstName")String firstName,
						  @RequestParam(value="lastName")String lastName, @RequestParam(value="phone")String phone,
						  @RequestParam(value="bio")String bio,@RequestParam(value="age")String age, Model model) {
		userService.update(id,firstName,lastName,phone,bio,age);
		//List<User> users = new ArrayList<>();
		//users.add(user);
		//model.addAttribute("user",users);
		return "redirect:/users";
	}
}
