package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.demo.converters.*;
import com.example.demo.dtos.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.services.OrdersService;
import com.example.demo.utils.WrapperResponse;

@RestController
public class OrdersController {
	
	@Autowired
	private OrdersService os;
	 
	private OrderConverter ordConv=new OrderConverter();
	
	
	
	//findAll
	@GetMapping(value="orders")
	public ResponseEntity<List<OrderDTO>> findAll(
			@RequestParam(value="pageSize",required=false,defaultValue="5") int pageSize,
			@RequestParam(value="pageNumber", required=false,defaultValue="0") int pageNumber
			){
				Pageable page= PageRequest.of(pageNumber, pageSize);
				
				List<OrderDTO> dtoOrders = ordConv.fromEntity(os.findAll(page));
				return new ResponseEntity<List<OrderDTO>> (dtoOrders,HttpStatus.OK);
		}
	
	//Find by ID	
		@GetMapping(value="orders/{id}")
		public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable("id") Long id) {
			Order order= os.findById(id);
			OrderDTO dtoOrder= ordConv.fromEntity(order);
			return new WrapperResponse<OrderDTO>(true,"Succes",dtoOrder).createResponse();
		}
		
	
	//Delete
		@DeleteMapping(value="orders/{id}")
		public ResponseEntity<?> delete(@PathVariable("id") Long id){
			os.delete(id);
			return new WrapperResponse<>(true,"succes",null).createResponse();
		}
	
	//Update
	@PutMapping(value="orders")
	public ResponseEntity<WrapperResponse<OrderDTO>> updateOrder(@RequestBody OrderDTO dtoOrder){
		Order upOrder = os.saveOrder(ordConv.fromDto(dtoOrder));
		OrderDTO upDtoOrder = ordConv.fromEntity(upOrder);
		return new WrapperResponse<OrderDTO>(true,"succes",upDtoOrder).createResponse(HttpStatus.ACCEPTED);
	}
	
	//Save
	@PostMapping(value="orders")
	public ResponseEntity<WrapperResponse<OrderDTO>> createOrder(@RequestBody OrderDTO dtoOrder){
		Order order = os.saveOrder(ordConv.fromDto(dtoOrder));
		OrderDTO newDtoOrder = ordConv.fromEntity(order);
		return new WrapperResponse<OrderDTO>(true,"succes",newDtoOrder).createResponse(HttpStatus.CREATED);
	}
	
	
	
	

}
