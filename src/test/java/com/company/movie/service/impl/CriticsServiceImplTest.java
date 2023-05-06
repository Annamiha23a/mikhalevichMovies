package com.company.movie.service.impl;

import com.company.movie.entity.Critics;
import com.company.movie.repository.CriticsRepository;
import com.company.movie.service.CriticsService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CriticsServiceImpl.class})
public class CriticsServiceImplTest {
	@Autowired
	private CriticsService criticsService;
	@MockBean
	private CriticsRepository criticsRepository;
	private final int id = 10;
	private final String name = "alex2411";
	private final List<Critics> list;
	private Critics inputCritics;
	private Critics outputCritics;


	public CriticsServiceImplTest() {
		inputCritics = Critics.builder()
				.id(id)
				.name(name)
				.build();
		outputCritics = Critics.builder()
				.id(id)
				.name(name)
				.build();
		list = Arrays.asList(outputCritics);
	}

	@Test
	public void findAllCritics_success() {
		when(criticsRepository.findAll()).thenReturn(list);
		assertEquals(list, criticsService.findAllCustomers());
	}

	@Test
	public void findCriticsById_success() {
		when(criticsRepository.findById(id)).thenReturn(Optional.of(outputCritics));
		Assertions.assertEquals(outputCritics, criticsService.findById(id));
	}

	@Test
	public void removeCritics_success() {
		when(criticsRepository.findById(id)).thenReturn(Optional.of(inputCritics));
		doNothing().when(criticsRepository).delete(inputCritics);
		criticsService.removeCustomer(id);
		verify(criticsRepository).delete(inputCritics);
	}

	@Test
	public void findCriticsById_invalidIndex() {
		when(criticsRepository.findById(id)).thenReturn(null);
		assertThrows(NullPointerException.class, ()-> criticsService.findById(id));
	}

}