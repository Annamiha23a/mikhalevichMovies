package com.company.movie.repository;

import com.company.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Movie, String> {
	Optional<Movie> findById(int id);
	@Query("select m from Movie m where title like %?1%")
	List<Movie> findByTitle(String title);
}
