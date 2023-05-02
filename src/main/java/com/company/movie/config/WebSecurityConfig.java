package com.company.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/registration")
				.permitAll()
				.antMatchers("/user/**").hasRole("ADMIN")
				.antMatchers("/users/**").hasRole("ADMIN")
				.antMatchers("/login/**").anonymous()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.loginPage("/")
				.loginProcessingUrl("/perform_login")
				.failureUrl("/login?error=true")
				.defaultSuccessUrl("/products")
				.and()
				.logout()
				.logoutSuccessUrl("/login")
				.and()
				.exceptionHandling()
				.and()
				.sessionManagement()
				.maximumSessions(1)
				.expiredUrl("/expired")
				.sessionRegistry(sessionRegistry);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**");
	}
}