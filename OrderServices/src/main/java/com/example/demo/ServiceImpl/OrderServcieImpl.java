package com.example.demo.ServiceImpl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Ordered;
import com.example.demo.External.client.PaymentRequest;
import com.example.demo.External.client.PaymentService;
import com.example.demo.External.client.ProductService;
import com.example.demo.Model.OrderRequest;
import com.example.demo.Model.OrderResponse;
import com.example.demo.Repository.OrderRepo;
import com.example.demo.service.OrderService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServcieImpl implements OrderService{

	
	@Autowired
	private OrderRepo orderrepo;
	
	
	@Autowired
	private  ProductService ps;
	
	
	@Autowired
	private PaymentService pays;
	
	
	@Override
	public long placeOrder(OrderRequest or) {
		//order entity -> save the data  with status order created
		//product service-> Block products(Reduce the quantity)
		//paymnet service-> payments-> success-> complete, ELSE
		// cancelled
		log.info("placinf order Request: {}", or);
		
		ps.reduceQuantity(or.getProductId(),or.getQuantity());
		
		log.info("Creating order with status Created");
		Ordered order = Ordered.builder()
				.amount(or.getTotalAmount())
				.orderStatus("CREATED")
				.productId(or.getProductId())
				.orderDate(Instant.now())
				.quantity(or.getQuantity())
				.build();
		
		order= orderrepo.save(order);
		
		log.info("Calling payment service to complete the payment");
		
		PaymentRequest pr= PaymentRequest.builder()
				.orderId(order.getId())
				.paymentMode(or.getPaymentMode())
				.amount(or.getTotalAmount())
				.build();
		
		String orderStatus = null;
		try {
			pays.doPayment(pr);
			log.info("Payment done successfully! Changing the Order status to PLACED");
			orderStatus= "PLACED";
		}catch(Exception e)
		{
			log.error("Error occured in payment! Changing payment status to PAYMENT_FAILED");
			orderStatus="PAYMENT_FAILED";
		}
		
		order.setOrderStatus(orderStatus);
		orderrepo.save(order);
		
		
		log.info("Order Placed Successfully with order Id : {}", order.getId());
		return order.getId();
	}


	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get Order details for orderId: {]",orderId);
		
		Ordered order= orderrepo.findById(orderId).get();
		
		OrderResponse orderResponse = OrderResponse.builder()
				.orderId(order.getId())
				.orderStatus(order.getOrderStatus())
				.amount(order.getAmount())
				.orderDate(order.getOrderDate())
				.build();
		return orderResponse;
	}

}
