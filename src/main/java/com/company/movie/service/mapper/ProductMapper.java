package com.company.movie.service.mapper;

import com.company.movie.dto.ProductDTO;
import com.company.movie.entity.Critics;
import com.company.movie.entity.Mark;
import com.company.movie.entity.Movie;
import com.company.movie.service.MovieService;
import com.company.movie.service.CriticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
	private MovieService movieService;
	private CriticsService criticsService;

	@Autowired
	public ProductMapper(MovieService movieService, CriticsService criticsService) {
		this.movieService = movieService;
		this.criticsService = criticsService;
	}

	public ProductDTO toDto(Mark mark) {
		return ProductDTO.builder()
				.id(mark.getId())
				.script(mark.getScript())
				.comment(mark.getComment())
				.duration(mark.getDuration())
				.rating(mark.getRating())
				.build();
	}

	public Mark toEntitySup(ProductDTO productDTO) {
		Movie movie = movieService.findById(Integer.valueOf(productDTO.getMovie()));
		Critics crititc = criticsService.findById(Integer.valueOf(productDTO.getCritic()));
		return Mark.builder()
				.id(productDTO.getId())
				.script(productDTO.getScript())
				.duration(productDTO.getDuration())
				.comment(productDTO.getComment())
				.rating(productDTO.getRating())
				.movie(movie)
				.critics(crititc)
				.build();
	}
}
