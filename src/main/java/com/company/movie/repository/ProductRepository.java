package com.company.movie.repository;

import com.company.movie.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Mark, String> {
	Optional<Mark> findById(int id);
}
