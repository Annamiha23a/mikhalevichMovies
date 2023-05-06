package com.company.movie.service;

import com.company.movie.entity.Movie;

import java.util.List;

public interface MovieService {
	Movie save(Movie movie);
	Movie findById(int id);
	List<Movie> findAllSuppliers();
	void removeSupplier(int id);

	List<Movie> listMovies(String title);
}
