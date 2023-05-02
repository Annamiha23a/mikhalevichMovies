package com.company.movie.service.impl;

import com.company.movie.entity.Critics;
import com.company.movie.repository.CustomerRepository;
import com.company.movie.service.CustomerService;
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
@ContextConfiguration(classes = {CustomerServiceImpl.class})
public class CriticsServiceImplTest {
	@Autowired
	private CustomerService customerService;
	@MockBean
	private CustomerRepository customerRepository;
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
	public void findAllCustomers_success() {
		when(customerRepository.findAll()).thenReturn(list);
		assertEquals(list, customerService.findAllCustomers());
	}

	@Test
	public void findCustomerById_success() {
		when(customerRepository.findById(id)).thenReturn(Optional.of(outputCritics));
		Assertions.assertEquals(outputCritics,customerService.findById(id));
	}

	@Test
	public void removeCustomer_success() {
		when(customerRepository.findById(id)).thenReturn(Optional.of(inputCritics));
		doNothing().when(customerRepository).delete(inputCritics);
		customerService.removeCustomer(id);
		verify(customerRepository).delete(inputCritics);
	}

	@Test
	public void findCustomerById_invalidIndex() {
		when(customerRepository.findById(id)).thenReturn(null);
		assertThrows(NullPointerException.class, ()->customerService.findById(id));
	}

}