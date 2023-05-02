package com.company.movie.service.impl;

import com.company.movie.entity.Movie;
import com.company.movie.repository.SupplierRepository;
import com.company.movie.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
	private SupplierRepository supplierRepository;

	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public Movie save(Movie movie) {
		return supplierRepository.save(movie);
	}

	@Override
	public Movie findById(int id) {
		return supplierRepository.findById(id)
				.orElseThrow(()->new NoSuchElementException());
	}

	@Override
	public List<Movie> findAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public void removeSupplier(int id) {
		Movie movie = findById(id);
		supplierRepository.delete(movie);
	}
	@Override
	public List<Movie> listMovies(String title){
		if (title != null) return supplierRepository.findByTitle(title);
		return supplierRepository.findAll();
	}

	public void updateS(Integer id, String title, String genre, String country, String director, String description){
		Optional<Movie> movie = supplierRepository.findById(id);
		if (!(movie.get() ==null)){
			movie.get().setTitle(title);
			movie.get().setGenre(genre);
			movie.get().setCountry(country);
			movie.get().setDirector(director);
			movie.get().setDescription(description);
		}
		supplierRepository.save(movie.get());
	}
}
