package com.company.movie.repository;

import com.company.movie.entity.Critics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Critics, String> {
	Optional<Critics> findById(int id);
}
