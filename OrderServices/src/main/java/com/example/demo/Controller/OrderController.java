package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.OrderRequest;
import com.example.demo.Model.OrderResponse;
import com.example.demo.service.OrderService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/Order")
@Log4j2
public class OrderController {

	@Autowired
	private OrderService os;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest or)
	{
		long orderId= os.placeOrder(or);
		log.info("Order Id: {}", orderId);
		return new ResponseEntity<>(orderId,HttpStatus.OK);
	}
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
		OrderResponse or= os.getOrderDetails(orderId);
		
		return new ResponseEntity<>(or,HttpStatus.OK);
	}
}
