package com.company.movie.service;

import com.company.movie.entity.Mark;
import com.company.movie.dto.ProductDTO;

import java.util.List;

public interface ProductService {
	Mark save(ProductDTO productDTO);

	Mark findById(int id);

	List<Mark> findAllProducts();

	void sellProduct(int productId, int quantity);

	void removeProduct(int productId);
}
