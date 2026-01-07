package com.ecom.kodewala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.kodewala.request.OrderRequest;
import com.ecom.kodewala.service.PlaceOrderService;

@RestController
public class PlaceOrderController {
	@Autowired
	private PlaceOrderService service;

	@PostMapping("/processOrder")
	public OrderRequest processOrder(@RequestBody OrderRequest request) {

		OrderRequest response = service.processOrder(request);
		return response;
	}
}
