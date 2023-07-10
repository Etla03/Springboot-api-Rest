package com.example.demo.services;

import com.example.demo.exceptions.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.validators.ProductValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findAll(Pageable page){
		List<Product> products = productRepo.findAll(page).toList();
		return products;
	}
	
	
	public Product findProduct(Long id) {
		try {
			Product product = productRepo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("no se encontro el producto"));
			return product;
		} catch(ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		
	}
	
	@Transactional
	public void deleteProduct(Long id) {

		try {
			Product delProduct=productRepo.findById(id)
		
					.orElseThrow(()->new NoDataFoundException("no se encontro el producto"));
			productRepo.delete(delProduct);
		
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		} 
	}
			
	
	@Transactional
	public Product saveProduct(Product p) {
		ProductValidator.validate(p);
		
		try {
				if(p.getId()==null) {
				Product newProduct = productRepo.save(p);
				return newProduct;
			}
			Product upProduct=productRepo.findById(p.getId())
					.orElseThrow(()->new NoDataFoundException("no se encontro el producto"));
			upProduct.setName(p.getName());
			upProduct.setPrice(p.getPrice());
			productRepo.save(upProduct);
			return upProduct;
		
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		
	}
}
