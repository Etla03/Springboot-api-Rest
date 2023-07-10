package com.example.demo.validators;

import com.example.demo.exceptions.*;
import com.example.demo.entity.Product;

public class ProductValidator {
	
	private final static int nameLength=100;
	public static void validate(Product product) {
		
		if(product.getName()==null || product.getName().isEmpty()) {
			throw new ValidateServiceException("el nombre es requerido");
		}
		
		if(product.getName().length()>nameLength) {
			throw new ValidateServiceException("el nombre es muy largo");
		}
		
		if(product.getPrice()==null) {
			throw new ValidateServiceException("el precio es requerido");
		}
		
		if(product.getPrice()<0) {
			throw new ValidateServiceException("el precio es invalido");
		}
		
	}

}
