package com.devsuperior.dscommerce.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return dto;
	}
	@GetMapping
	public List<ProductDTO> findAll() {
		return service.findAll();
		
	}

}
