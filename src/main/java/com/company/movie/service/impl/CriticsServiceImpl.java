package com.company.movie.service.impl;

import com.company.movie.entity.Critics;
import com.company.movie.repository.CriticsRepository;
import com.company.movie.service.CriticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CriticsServiceImpl implements CriticsService {
	private CriticsRepository criticsRepository;

	@Autowired
	public CriticsServiceImpl(CriticsRepository criticsRepository) {
		this.criticsRepository = criticsRepository;
	}

	@Override
	public Critics save(Critics critics) {
		return criticsRepository.save(critics);
	}

	@Override
	public Critics findById(int id) {
		return criticsRepository.findById(id)
				.orElseThrow(()-> new NoSuchElementException());
	}

	@Override
	public List<Critics> findAllCustomers() {
		return criticsRepository.findAll();
	}

	@Override
	public void removeCustomer(int id) {
		Critics critics = findById(id);
		criticsRepository.delete(critics);
	}

	public void update(Integer id, String name, String surname, String specialty, String experience, String university){
		Optional<Critics> critic = criticsRepository.findById(id);
		if (!(critic.get()==null)){
			critic.get().setName(name);
			critic.get().setSurname(surname);
			critic.get().setSpecialty(specialty);
			critic.get().setExperience(experience);
			critic.get().setUniversity(university);
		}
		criticsRepository.save(critic.get());
	}
}
