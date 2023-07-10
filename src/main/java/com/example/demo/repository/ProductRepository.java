package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

import org.springframework.data.jpa.repository.*;

@Repository 
public interface ProductRepository extends JpaRepository<Product, Long>{

}
