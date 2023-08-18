package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Product;
import com.cdac.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public Product findProduct(int id) {
		Optional<Product>  product =  productRepo.findById(id);
		if (product.isPresent())
			return product.get();
		 throw new IllegalArgumentException("Product not found with ID: " + id);
		
	}
	
	public Product updateQty(Product product) {
		return productRepo.save(product);
	}
	
	public List<Product> showAll(){
		return productRepo.findAll();
	}
	
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProduct(Product product) {
		productRepo.delete(product);
		System.out.println("Product Deleted");
	}
	
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
}
