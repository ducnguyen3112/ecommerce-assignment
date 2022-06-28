package com.nashtech.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.ProductDto;
import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import com.nashtech.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ModelMapper modelMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,
			ModelMapper modelMapper) {
		super();
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ProductDto> findAllProductDtos() {
		List<Product> products = productRepository.findAll();
		return modelMapper.map(products, new TypeToken<List<ProductDto>>() {
		}.getType());
	}

	@Override
	public ProductDto findProductDtoById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Did not find product with id = " + id));
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		productDto.setCreatedAt(new Date());
		productDto.setModifiedAt(new Date());
		productDto.setStatus(1);
		Product product = productRepository
				.save(modelMapper.map(productDto, Product.class));
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {
		Product product = productRepository.findById(productDto.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Did not find product has id = " + productDto.getId()));
		modelMapper.map(productDto,product);
		product=productRepository.save(product);
		return modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ResponseMessageDto deleteProduct(Long id) {
		productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Did not find product with id = " + id));
			productRepository.deleteById(id);
		return new ResponseMessageDto(HttpStatus.ACCEPTED,
				"Deleted product with id= " + id, LocalDateTime.now());
	}

}
