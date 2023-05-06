package com.company.movie.service.impl;

import com.company.movie.entity.Movie;
import com.company.movie.repository.MovieRepository;
import com.company.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
	private MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie findById(int id) {
		return movieRepository.findById(id)
				.orElseThrow(()->new NoSuchElementException());
	}

	@Override
	public List<Movie> findAllSuppliers() {
		return movieRepository.findAll();
	}

	@Override
	public void removeSupplier(int id) {
		Movie movie = findById(id);
		movieRepository.delete(movie);
	}
	@Override
	public List<Movie> listMovies(String title){
		if (title != null) return movieRepository.findByTitle(title);
		return movieRepository.findAll();
	}

	public void updateS(Integer id, String title, String genre, String country, String director, String description){
		Optional<Movie> movie = movieRepository.findById(id);
		if (!(movie.get() ==null)){
			movie.get().setTitle(title);
			movie.get().setGenre(genre);
			movie.get().setCountry(country);
			movie.get().setDirector(director);
			movie.get().setDescription(description);
		}
		movieRepository.save(movie.get());
	}
}
