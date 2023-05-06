package com.company.movie.controller;

import com.company.movie.entity.Movie;
import com.company.movie.service.MovieService;
import com.company.movie.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {
	private MovieService movieService;
	private final MovieServiceImpl movieServiceImpl;

	@Autowired
	public MovieController(MovieService supplierService, MovieServiceImpl movieService) {
		this.movieService = supplierService;
		this.movieServiceImpl=movieService;
	}

	@GetMapping("/add-supplier")
	public String addSupplier(Model model) {
		model.addAttribute(new Movie());
		return "add-supplier";
	}

	@PostMapping("/add-supplier")
	public String addSupplier(@ModelAttribute Movie movie) {
		movieService.save(movie);
		return "redirect:suppliers";
	}
	@GetMapping(value = "/supplier")
	public String product(@RequestParam(name = "title", required = false) String title, Model model){
		model.addAttribute("suppliers", movieService.listMovies(title));
		return "suppliers";
	}

	@GetMapping("/suppliersU")
	public String findAllSupplier(Model model) {
		List<Movie> movies = movieService.findAllSuppliers();
		model.addAttribute("suppliers", movies);
		return "suppliersU";
	}
	@GetMapping("/suppliers")
	public String findAllSuppliers(Model model) {
		List<Movie> movies = movieService.findAllSuppliers();
		model.addAttribute("suppliers", movies);
		return "suppliers";
	}
	@GetMapping("/mark")
	public String markAllSuppliers(Model model) {
		List<Movie> suppliers = movieService.findAllSuppliers();
		model.addAttribute("movies",suppliers);
		return "mark";
	}

	@GetMapping("/calculation")
	public String calculation(Model model) {

		return "calculation";
	}
	@GetMapping("/supplier/{id}")
	public String findSupplier(@PathVariable(value = "id") int id, Model model) {
		Movie movie = movieService.findById(id);
		List<Movie> movies = new ArrayList<>();
		movies.add(movie);
		model.addAttribute("supplier", movies);
		return "supplier-details";
	}

	@PostMapping("/supplier/{id}/remove")
	public String removeSupplier(@PathVariable(value = "id") int id, Model model) {
		movieService.removeSupplier(id);
		return "suppliers";
	}

	@GetMapping("/supplier/{id}/update")
	public String updateSupplier(@PathVariable(value = "id") int id, Model model){
		Movie movie = movieService.findById(id);
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
		movieServiceImpl.updateS(id,title,genre,country,director,description);
		return "redirect:/suppliers";
	}

}
