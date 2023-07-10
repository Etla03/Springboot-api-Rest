package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="order_lines")
public class OrderLine {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_order")
	private Order order;
	
	
	@ManyToOne
	@JoinColumn(name="id_Product",nullable=false,updatable=false)
	private Product product;
	
	@Column(name="price",nullable=false)
	private Double price;
	
	@Column(name="quantity",nullable=false )
	private Double quantity;
	
	@Column(name="total",nullable=false)
	private Double total;
	
	public void updateTotal() {
		total=quantity*price;
	}
	
	
	public void setProduct(Product product) {
		this.product=product;
		setPrice(product.getPrice());
	}
	public void setPrice(Double price) {
		this.price=price;
		updateTotal();
	}
	
	public void setQuantity(Double quantity ) {
		this.quantity=quantity;
		updateTotal();
	}
}
