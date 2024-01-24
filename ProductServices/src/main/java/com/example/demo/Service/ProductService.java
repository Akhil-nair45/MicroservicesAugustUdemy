package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Product;

public interface ProductService {

	public Product addProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public Product getById(Long productId);

	public String reduceQuantity(long productId, long quantity);
	
	
	
}
