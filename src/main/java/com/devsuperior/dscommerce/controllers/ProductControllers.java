package com.devsuperior.dscommerce.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.service.ProductService;


@RestController
@RequestMapping(value = "/products")
public class ProductControllers {
	@Autowired
	private ProductService service;
	
	@GetMapping(value ="/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);	
		return dto;
	}
}
