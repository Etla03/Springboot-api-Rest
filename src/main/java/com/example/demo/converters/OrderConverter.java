package com.example.demo.converters;


import java.time.format.DateTimeFormatter;
import com.example.demo.dtos.OrderDTO;
import com.example.demo.entity.Order;


public class OrderConverter extends AbstractConverter<Order, OrderDTO>{

	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	private OrderLineConverter olConverter=new OrderLineConverter();
	@Override
	public Order fromDto(OrderDTO dto) {
		if(dto==null) return null;
		return  Order.builder()
				.id(dto.getId())
				.lines(olConverter.fromDto(dto.getLines()))
				.total(dto.getTotal()).build();

	}

	@Override
	public OrderDTO fromEntity(Order entity) {
		if(entity==null) return null;
		return  OrderDTO.builder()
				.id(entity.getId())
				.regDate(entity.getRegDate().format(format))
				.lines(olConverter.fromEntity(entity.getLines()))
				.total(entity.getTotal())
				.build();
	}
	}
	

