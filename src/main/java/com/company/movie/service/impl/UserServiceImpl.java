package com.company.movie.service.impl;

import com.company.movie.entity.Role;
import com.company.movie.entity.User;
import com.company.movie.mail.EmailDetails;
import com.company.movie.mail.EmailService;
import com.company.movie.repository.RoleRepository;
import com.company.movie.repository.UserRepository;
import com.company.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private List<User> list ;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private EmailService emailService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.list = userRepository.findAll();
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User save(User user) {
		EmailDetails details = new EmailDetails(user.getUsername(),"Добро пожаловать на сервис оценки кинофильмов. Ваш логин:" + user.getUsername() +". Ваш пароль:"+user.getPassword(), "Регистрация на сервисе", null);
	    user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role role = list.isEmpty()? roleRepository.findByRole("ROLE_ADMIN") : roleRepository.findByRole("ROLE_USER");
		//Role role = roleRepository.findByRole("ROLE_ADMIN");
		user.setRoles(new HashSet<>(List.of(role)));
		if (user.getUsername()!=null) {
			emailService.sendSimpleMail(details);
		}
		return userRepository.save(user);
	}

	public void banUser(Integer id) {
		User user = userRepository.findUserById(id).orElse(null);
		if (user!=null){
			if (user.isActive()){
				user.setActive(false);
			} else {
				user.setActive(true);
			}
		}
		userRepository.save(user);
	}

	public void update(Integer id, String firstName, String lastName, String phone, String bio, String age){
		User user = userRepository.findUserById(id).orElse(null);
		if (user!=null){
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(phone);
			user.setBio(bio);
			user.setAge(age);
		}
		userRepository.save(user);
	}

	@Override
	public void removeUser(int id) {
		User user = findUserById(id);
		userRepository.delete(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username)
				.orElseThrow(() -> new NoSuchElementException());
	}

	@Override
	public User findUserById(int id) {
		return userRepository.findUserById(id)
				.orElseThrow(()->new NoSuchElementException());
	}
}
