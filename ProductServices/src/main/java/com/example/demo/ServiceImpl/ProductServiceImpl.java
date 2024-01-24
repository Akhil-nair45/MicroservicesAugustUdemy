package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Service.ProductService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo pr;
	
	
	@Override
	public Product addProduct(Product product) {
		return pr.save(product);
		
	}


	@Override
	public List<Product> getAllProducts() {
		return pr.findAll();
	}


	@Override
	public Product getById(Long productId) {
		return pr.findById(productId).get();
	}


	@Override
	public String reduceQuantity(long productId, long quantity) {
		Product product=pr.findById(productId).get();
		
		if(product.getQuantity() < quantity)
		{
			return "Product does not have sufficient quantity";
		}
		else {
		
		product.setQuantity(product.getQuantity()- quantity);
		pr.save(product);
		log.info("Product Quantity updated successfully!");
		}
		
		return "some error occured!";
		
	}

}
