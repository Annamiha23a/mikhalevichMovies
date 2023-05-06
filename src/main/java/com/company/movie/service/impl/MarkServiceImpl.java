package com.company.movie.service.impl;

import com.company.movie.dto.ProductDTO;
import com.company.movie.entity.Mark;
import com.company.movie.repository.MarkRepository;
import com.company.movie.service.ProductService;
import com.company.movie.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MarkServiceImpl implements ProductService {
	private MarkRepository markRepository;
	private ProductMapper productMapper;

	@Autowired
	public MarkServiceImpl(MarkRepository markRepository, ProductMapper productMapper) {
		this.markRepository = markRepository;
		this.productMapper = productMapper;
	}

	@Override
	public Mark save(ProductDTO productDTO) {
		Mark mark = productMapper.toEntitySup(productDTO);
		return markRepository.save(mark);
	}

	@Override
	public Mark findById(int id) {
		return markRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException());
	}

	@Override
	public List<Mark> findAllProducts() {
		return markRepository.findAll();
	}

	@Override
	public void sellProduct(int productId, int quantity) {
		Mark mark = findById(productId);
		System.out.println(mark.getRating());
		System.out.println(quantity);
		if (mark.getRating() == quantity) {
			markRepository.delete(mark);
		}
		if (mark.getRating() > quantity) {
			mark.setRating(mark.getRating() - quantity);
			markRepository.saveAndFlush(mark);
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void removeProduct(int productId) {
		Mark mark = findById(productId);
		markRepository.delete(mark);
	}

	public void update(Integer id, String script, Integer rating, Integer duration, String comment){
		Optional<Mark> mark = markRepository.findById(id);
		if (!(mark.get()==null)){
			mark.get().setScript(script);
			mark.get().setRating(rating);
			mark.get().setDuration(duration);
			mark.get().setComment(comment);
		}
		markRepository.save(mark.get());
	}
}
