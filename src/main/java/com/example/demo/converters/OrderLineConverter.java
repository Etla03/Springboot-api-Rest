package com.example.demo.converters;

import com.example.demo.dtos.OrderLineDTO;
import com.example.demo.entity.OrderLine;

public class OrderLineConverter extends AbstractConverter<OrderLine, OrderLineDTO>{

	private ProductConverter pConverter = new ProductConverter();
	
	
	@Override
	public OrderLine fromDto(OrderLineDTO dto) {
		if(dto==null) return null;
		
		return OrderLine.builder()
				.product(pConverter.fromDto(dto.getProduct()))
				.price(dto.getPrice())
				.quantity(dto.getQuantity())
				.total(dto.getTotal())
				.build();
	}
	
	
	@Override
	public OrderLineDTO fromEntity(OrderLine entity) {
		if(entity==null) return null;
		
		return OrderLineDTO.builder()
				.id(entity.getId())
				.product(pConverter.fromEntity(entity.getProduct()))
				.price(entity.getPrice())
				.quantity(entity.getQuantity())
				.total(entity.getTotal())
				.build();
	}

}
