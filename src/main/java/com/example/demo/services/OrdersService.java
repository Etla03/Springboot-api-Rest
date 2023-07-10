package com.example.demo.services;

import com.example.demo.entity.Order;
import com.example.demo.repository.*;



import com.example.demo.exceptions.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdersService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	//findAll
	public List<Order> findAll(Pageable page){
		List<Order> orders = orderRepo.findAll(page).toList();
		return orders;
		
	}
	
	//findById
	
	public Order findById(Long id) {
		try {
		Order order = orderRepo.findById(id)
				.orElseThrow(()-> new NoDataFoundException("No se encontro la orden"));
		return order;
		}catch(ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
			
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Transactional
	public void delete(Long id) {	
		orderRepo.delete(this.findById(id));
	}
	
	@Transactional
	public Order saveOrder(Order order) {
		try {
				if(order.getId()==null) {
					Order newOrder=orderRepo.save(order);
					return newOrder;
				}
				Order upOrder=this.findById(order.getId());
				upOrder.setLines(order.getLines());
				upOrder.setTotal(order.getTotal());
				orderRepo.save(upOrder);
				return upOrder;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		
	}
}
