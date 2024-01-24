package com.example.demo.service;

import com.example.demo.Model.OrderRequest;
import com.example.demo.Model.OrderResponse;

public interface OrderService {

	long placeOrder(OrderRequest or);

	OrderResponse getOrderDetails(long orderId);

}
