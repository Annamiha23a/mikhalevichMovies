package com.company.movie.service.impl;

import com.company.movie.entity.Critics;
import com.company.movie.repository.CustomerRepository;
import com.company.movie.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Critics save(Critics critics) {
		return customerRepository.save(critics);
	}

	@Override
	public Critics findById(int id) {
		return customerRepository.findById(id)
				.orElseThrow(()-> new NoSuchElementException());
	}

	@Override
	public List<Critics> findAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void removeCustomer(int id) {
		Critics critics = findById(id);
		customerRepository.delete(critics);
	}

	public void update(Integer id, String name, String surname, String specialty, String experience, String university){
		Optional<Critics> critic = customerRepository.findById(id);
		if (!(critic.get()==null)){
			critic.get().setName(name);
			critic.get().setSurname(surname);
			critic.get().setSpecialty(specialty);
			critic.get().setExperience(experience);
			critic.get().setUniversity(university);
		}
		customerRepository.save(critic.get());
	}
}
