package com.company.movie.service;

import com.company.movie.entity.Critics;

import java.util.List;

public interface CustomerService {
	Critics save(Critics critics);
	Critics findById(int id);
	List<Critics> findAllCustomers();
	void removeCustomer(int id);
}
