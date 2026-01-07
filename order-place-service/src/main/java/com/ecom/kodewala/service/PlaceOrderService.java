package com.ecom.kodewala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.kodewala.repository.PlaceOrderRepository;
import com.ecom.kodewala.request.OrderRequest;

@Service
public class PlaceOrderService {
	@Autowired
	private PlaceOrderRepository repository;

	public OrderRequest processOrder(OrderRequest request) {

		request.setStatus("ORDER_PLACED");

		OrderRequest savedOrder = repository.save(request);

		return savedOrder;
	}
}
