package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {

	
	@Autowired
	private ProductService ps;
	
	@PostMapping("/add")
	public ResponseEntity<?> addproduct(@RequestBody Product product)
	{
		return new ResponseEntity<>(ps.addProduct(product),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll()
	{
		return new ResponseEntity<>(ps.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<?> getById(@PathVariable long productId)	
	{
		return new ResponseEntity<>(ps.getById(productId),HttpStatus.OK);
	}
	
	@PutMapping("reduceQuantity/{productId}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable long productId,@RequestParam long quantity)
	{
		ps.reduceQuantity(productId, quantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
