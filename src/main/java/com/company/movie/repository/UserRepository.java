package com.company.movie.repository;

import com.company.movie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	List<User> findAll();

	List<User> findByActive(boolean active);

	Optional<User> findUserById(int id);

	Optional<User> findUserByUsername(String username);
}
