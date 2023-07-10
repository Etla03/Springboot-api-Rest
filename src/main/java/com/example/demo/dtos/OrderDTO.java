package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import com.example.demo.entity.OrderLine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class OrderDTO {

	
	private Long id;
	private String regDate;
	private List<OrderLineDTO> lines;
	private Double total;
}
