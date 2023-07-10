package com.example.demo.controllers;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;
import com.example.demo.utils.WrapperResponse;
import com.example.demo.converters.*;
import java.util.List;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;


@RestController
public class ProductsController {

	@Autowired
	private ProductService ps;
	//private ProductRepository productRepo;
	
	private ProductConverter converter = new ProductConverter();

	@GetMapping(value="products")
	public ResponseEntity<List<ProductDTO>> listarTodos(
			@RequestParam(value="pageNumber", required=false, defaultValue="0") int pageNumber,
			@RequestParam(value="pageSize",required=false,defaultValue="2") int pageZize ){
		
		Pageable page =PageRequest.of(pageNumber, pageZize);
		
		List<ProductDTO> products = converter.fromEntity(ps.findAll(page));

		return new ResponseEntity<List<ProductDTO>> (products , HttpStatus.OK);
	}
	
	
	@GetMapping(value="products/{id}")
	public ResponseEntity<WrapperResponse<ProductDTO>> encontrarProducto(@PathVariable("id") Long id) {
			Product product = ps.findProduct(id);
			ProductDTO dtoProduct=converter.fromEntity(product);
			return new WrapperResponse<ProductDTO>(true,"succes",dtoProduct).createResponse();
		}
		
	
	@PostMapping(value="products")
	public  ResponseEntity<WrapperResponse<ProductDTO>> crearProducto(@RequestBody ProductDTO p) {
		Product newProduct=ps.saveProduct(converter.fromDto(p));
		ProductDTO dto=converter.fromEntity(newProduct);
		return new WrapperResponse<ProductDTO>(true,"Succes",dto).createResponse(HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="products")
	public ResponseEntity<WrapperResponse<ProductDTO>> actualizar(@RequestBody ProductDTO p) {
		Product upProduct = ps.saveProduct(converter.fromDto(p));
		ProductDTO dtoProduct=converter.fromEntity(upProduct);
		return new WrapperResponse<ProductDTO>(true,"Succes",dtoProduct).createResponse(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="products/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long id) {
		ps.deleteProduct(id);
		return new WrapperResponse<>(true,"Succes",null).createResponse(HttpStatus.OK);
	}
	
}
