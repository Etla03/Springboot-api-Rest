package com.example.demo.dtos;

import com.example.demo.entity.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderLineDTO {

	private Long id;
	private ProductDTO product;
	private Double price;
	private Double quantity;
	private Double total;
	
}
