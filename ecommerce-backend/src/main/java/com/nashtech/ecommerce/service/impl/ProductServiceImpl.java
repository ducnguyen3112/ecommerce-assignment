package com.nashtech.ecommerce.service.impl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nashtech.ecommerce.dto.ProductDto;
import com.nashtech.ecommerce.dto.ResponseMessageDto;
import com.nashtech.ecommerce.entity.Product;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.ProductRepository;
import com.nashtech.ecommerce.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository productRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,ModelMapper modelMapper) {
		super();
		this.productRepository=productRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public List<ProductDto> findAllProductDtos() {
		List<Product> products=productRepository.findAll();	
		return  modelMapper.map(products, new TypeToken<List<ProductDto>>(){}.getType());
	}
	@Override
	public ProductDto findProductDtoById(Long id) {
		Optional<Product> optional=productRepository.findById(id);
		Product product=null;
		if (optional.isPresent()) {
			product=optional.get();
		}else {
			throw new ResourceNotFoundException("Did not find product with id = "+id);
		}
		return modelMapper.map(product , ProductDto.class);
	}
	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		
		productDto.setId((long) 0);
		productDto.setCreatedAt(new Date());
		productDto.setModifiedAt(new Date());
		productDto.setStatus(1);
		Product product = productRepository.save(modelMapper.map(productDto,Product.class));
		return modelMapper.map(product , ProductDto.class);
	}
	@Override
	public ProductDto updateProduct(Map<Object, Object> fields,Long id) {
		Optional<Product> optional=productRepository.findById(id);
		Product Product;
		if (optional.isPresent()) {
			Product=optional.get();
			fields.forEach((k, v) -> {
				Field field=ReflectionUtils.findRequiredField(Product.class, (String) k);
				field.setAccessible(true);
				ReflectionUtils.setField(field, Product, v);
			});
		}else {
			throw new ResourceNotFoundException("Did not find product with id = "+id);
		}
		return modelMapper.map( Product, ProductDto.class);
	}
	@Override
	public ResponseMessageDto deleteProduct(Long id) {
		Optional<Product> optional=productRepository.findById(id);
		if (!optional.isPresent()) {
			throw new ResourceNotFoundException("Did not find product with id = "+id);
			
		}else {
			productRepository.deleteById(id);
		}
		
		return new ResponseMessageDto(HttpStatus.ACCEPTED, "Deleted product with id= "+id, LocalDateTime.now());
	}
	
	
}
