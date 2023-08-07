package com.devsuperior.dscommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.controllers.EntityResultAccessException;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Recurso não encontrato"));
		return new ProductDTO(product);		
	}
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}
	 @Transactional
	    public ProductDTO insert(ProductDTO dto) {
	        Product entity = new Product();
	        copyDtoToEntity(dto, entity);
	        entity = repository.save(entity);
	        return new ProductDTO(entity);
	    }
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Recurso não encontrado");
			
		}
		
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id){
		try {
	        	repository.deleteById(id);    		
		}
		catch(EntityResultAccessException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
		
}
