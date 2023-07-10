package com.example.demo.converters;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.entity.Product;

public class ProductConverter extends AbstractConverter<Product,ProductDTO> {

	@Override
	public Product fromDto(ProductDTO dto) {
		if(dto==null)return null;
		Product product=Product.builder()
				.id(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.build();
		return product;
	}

	@Override
	public ProductDTO fromEntity(Product entity) {
		if(entity==null) return null;
		ProductDTO dto =ProductDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.price(entity.getPrice())
				.build();
		return dto;
	}

}
