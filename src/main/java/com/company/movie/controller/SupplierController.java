package com.company.movie.controller;

import com.company.movie.entity.Movie;
import com.company.movie.service.SupplierService;
import com.company.movie.service.impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SupplierController {
	private SupplierService supplierService;
	private final SupplierServiceImpl movieService;

	@Autowired
	public SupplierController(SupplierService supplierService, SupplierServiceImpl movieService) {
		this.supplierService = supplierService;
		this.movieService=movieService;
	}

	@GetMapping("/add-supplier")
	public String addSupplier(Model model) {
		model.addAttribute(new Movie());
		return "add-supplier";
	}

	@PostMapping("/add-supplier")
	public String addSupplier(@ModelAttribute Movie movie) {
		supplierService.save(movie);
		return "redirect:suppliers";
	}
	@GetMapping(value = "/supplier")
	public String product(@RequestParam(name = "title", required = false) String title, Model model){
		model.addAttribute("suppliers", supplierService.listMovies(title));
		return "suppliers";
	}

	@GetMapping("/suppliersU")
	public String findAllSupplier(Model model) {
		List<Movie> movies = supplierService.findAllSuppliers();
		model.addAttribute("suppliers", movies);
		return "suppliersU";
	}
	@GetMapping("/suppliers")
	public String findAllSuppliers(Model model) {
		List<Movie> movies = supplierService.findAllSuppliers();
		model.addAttribute("suppliers", movies);
		return "suppliers";
	}
	@GetMapping("/mark")
	public String markAllSuppliers(Model model) {
		List<Movie> suppliers = supplierService.findAllSuppliers();
		model.addAttribute("movies",suppliers);
		return "mark";
	}

	@GetMapping("/calculation")
	public String calculation(Model model) {

		return "calculation";
	}
	@GetMapping("/supplier/{id}")
	public String findSupplier(@PathVariable(value = "id") int id, Model model) {
		Movie movie = supplierService.findById(id);
		List<Movie> movies = new ArrayList<>();
		movies.add(movie);
		model.addAttribute("supplier", movies);
		return "supplier-details";
	}

	@PostMapping("/supplier/{id}/remove")
	public String removeSupplier(@PathVariable(value = "id") int id, Model model) {
		supplierService.removeSupplier(id);
		return "suppliers";
	}

	@GetMapping("/supplier/{id}/update")
	public String updateSupplier(@PathVariable(value = "id") int id, Model model){
		Movie movie = supplierService.findById(id);
		List<Movie> movies = new ArrayList<>();
		movies.add(movie);
		model.addAttribute("supplier",movies);
		return "updateSupplier";
	}

	@PostMapping("/supplier/{id}/updateS")
	public String updateU(@PathVariable(value="id")int id, @RequestParam(value="title")String title,
						  @RequestParam(value="genre")String genre, @RequestParam(value="country")String country,
						  @RequestParam(value="director")String director, @RequestParam(value="description")String description,
						  Model model){
		movieService.updateS(id,title,genre,country,director,description);
		return "redirect:/suppliers";
	}

}
