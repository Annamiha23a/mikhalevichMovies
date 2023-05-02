package com.company.movie.service;

import com.company.movie.entity.User;

import java.util.List;

public interface UserService {
	List<User> findAllUsers();

	User save(User user);

	void removeUser(int id);

	User findUserByUsername(String username);

	User findUserById(int id);
}
