package com.company.movie.controller;

import com.company.movie.entity.Critics;
import com.company.movie.service.CustomerService;
import com.company.movie.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
	private final CustomerService customerService;
	private final CustomerServiceImpl criticService;

	@Autowired
	public CustomerController(CustomerService customerService, CustomerServiceImpl criticService) {
		this.customerService = customerService;
		this.criticService = criticService;
	}

	@GetMapping("/add-customer")
	public String addCustomer(Model model) {
		model.addAttribute(new Critics());
		return "add-customer";
	}

	@PostMapping("/add-customer")
	public String addCustomer(@ModelAttribute Critics critics) {
		customerService.save(critics);
		return "redirect:customers";
	}

	@GetMapping("/customers")
	public String findAllCustomers(Model model) {
		List<Critics> critics = customerService.findAllCustomers();
		model.addAttribute("customers", critics);
		return "customers";
	}

	@GetMapping("/customer/{id}")
	public String findCustomer(@PathVariable(value = "id") int id, Model model) {
		Critics critic = customerService.findById(id);
		List<Critics> critics = new ArrayList<>();
		critics.add(critic);
		model.addAttribute("customer", critics);
		return "customer-details";
	}

	@PostMapping("/customer/{id}/remove")
	public String removeCustomer(@PathVariable(value = "id") int id, Model model) {
		customerService.removeCustomer(id);
		return "customers";
	}

	@GetMapping("/customer/{id}/update")
	public String updateCustomer(@PathVariable(value="id")int id, Model model){
		Critics critic = customerService.findById(id);
		List<Critics> critics = new ArrayList<>();
		critics.add(critic);
		model.addAttribute("customer", critics);
		return "updateCustomer";
	}
	@PostMapping("/customer/{id}/updateC")
	public String updateC(@PathVariable(value = "id")int id, @RequestParam(value = "name")String name,
						   @RequestParam(value = "surname")String surname, @RequestParam(value = "specialty")String specialty,
						   @RequestParam(value = "experience")String experience, @RequestParam(value="university")String university,
						   Model model){
		criticService.update(id,name,surname,specialty,experience,university);
		return "redirect:/customers";
	}

}
