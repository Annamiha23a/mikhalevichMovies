package com.company.movie.service.impl;

import com.company.movie.entity.Role;
import com.company.movie.entity.User;
import com.company.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(userName);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username: %s", userName));
		}
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		for (Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return new ArrayList<>(roles);
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isActive(), true, true, true, authorities);
	}
}
