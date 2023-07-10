package com.example.demo.entity;


import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

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
@Table(name="orders")
public class Order {
	
	@Id
	@Column(name="id",nullable=false,updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="reg_date",nullable=false,updatable=false)
	private LocalDate regDate;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderLine> lines;
	
	@Column(name="total",nullable=false)
	private Double total;
	
	private void updateTotal() {
		for(OrderLine line: lines) {
			total+=line.getTotal();
		}
	}
	
	public void addOrderLines(List<OrderLine> newLines) {
		List<OrderLine> oldLines = getLines();
		for (OrderLine line : newLines) {
			line.setOrder(this);
			oldLines.add(line);
		}
		updateTotal();
		
	}
	
	public void addOrderLine(OrderLine line) {
		List<OrderLine> oldLines = getLines();
		line.setOrder(this);
		oldLines.add(line);
		updateTotal();
	}
	
	public void setLines(List<OrderLine> lines) {
		this.lines=lines;
		updateTotal();
	}
	
	public List<OrderLine> getLines(){
		if (lines==null) {
			lines = new ArrayList<OrderLine>();
		}
		return lines;
	}
	
	
	

}
