package com.company.movie.controller;

import com.company.movie.dto.ProductDTO;
import com.company.movie.entity.Critics;
import com.company.movie.entity.Mark;
import com.company.movie.entity.Movie;
import com.company.movie.service.ProductService;
import com.company.movie.service.MovieService;
import com.company.movie.service.impl.MarkServiceImpl;
import com.company.movie.service.mapper.ProductMapper;
import com.company.movie.service.CriticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarkController {
	private final ProductService productService;
	private final MovieService movieService;
	private final CriticsService criticsService;
	private final ProductMapper productMapper;
	private final MarkServiceImpl markService;

	@Autowired
	public MarkController(ProductService productService, MovieService movieService,
						  CriticsService criticsService, ProductMapper productMapper,
						  MarkServiceImpl markService) {
		this.productService = productService;
		this.movieService = movieService;
		this.criticsService = criticsService;
		this.productMapper = productMapper;
		this.markService = markService;
	}

	@GetMapping("/add-product")
	public String addProduct(Model model) {
		List<Movie> movies = movieService.findAllSuppliers();
		model.addAttribute("suppliers", movies);
		List<Critics> critics = criticsService.findAllCustomers();
		model.addAttribute("critics", critics);
		model.addAttribute(new ProductDTO());
		return "add-product";
	}

	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute ProductDTO productDTO) {
		productService.save(productDTO);
		return "add-product";
	}

	@GetMapping("/sell-product")
	public String sellProduct(Model model) {
		List<Critics> critics = criticsService.findAllCustomers();
		model.addAttribute("customers", critics);
		List<Mark> marks = productService.findAllProducts();
		model.addAttribute("products", marks);
		model.addAttribute(new ProductDTO());
		return "sell-product";
	}

	@PostMapping("/sell-product/{id}")
	public String sellProduct(@ModelAttribute ProductDTO productDTO) {
		//productService.sellProduct(id, quantity);
		productService.save(productDTO);
		return "sell-product";
	}

	@GetMapping("/products")
	public String findAllProducts(Model model) {
		List<Mark> marks = productService.findAllProducts();
		model.addAttribute("products", marks);
		return "products";
	}

	@GetMapping()
	public String home() {
		return "redirect:products";
	}

	@GetMapping("/product/{id}")
	public String findProduct(@PathVariable(value = "id") int id, Model model) {
		Mark mark = productService.findById(id);
		List<Mark> marks = new ArrayList<>();
		marks.add(mark);
		model.addAttribute("product", marks);
		return "product-details";
	}

	@PostMapping("/product/{id}/remove")
	public String removeProduct(@PathVariable(value = "id") int id, Model model) {
		productService.removeProduct(id);
		return "products";
	}
	@GetMapping("/product/{id}/update")
	public String updateProduct(@PathVariable(value="id")int id, Model model){
		Mark mark = productService.findById(id);
		List<Mark> marks = new ArrayList<>();
		marks.add(mark);
		model.addAttribute("product", marks);
		List<Movie> movies = movieService.findAllSuppliers();
		model.addAttribute("suppliers", movies);
		List<Critics> critics = criticsService.findAllCustomers();
		model.addAttribute("critics", critics);
		model.addAttribute(new ProductDTO());
		return "updateProductU";
	}
	@PostMapping("/product/{id}/updateP")
	public String updateP(@PathVariable(value="id")int id, @RequestParam(value="script")String script,
						  @RequestParam(value = "rating")int rating, @RequestParam(value = "duration")int duration,
						  @RequestParam(value = "comment")String comment, Model model){
		markService.update(id,script,rating,duration,comment);
		return "redirect:/products";
	}
}
