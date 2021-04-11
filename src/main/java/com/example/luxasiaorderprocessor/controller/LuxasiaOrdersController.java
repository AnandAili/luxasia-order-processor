package com.example.luxasiaorderprocessor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.luxasiaorderprocessor.model.OrderDetail;
import com.example.luxasiaorderprocessor.repository.OrderRepository;

@RestController
public class LuxasiaOrdersController {

	@Autowired
	OrderRepository orderRepository;


	@GetMapping("/orders/{merketPlace}") // orders/lazada
	public List<OrderDetail> getOrders(@PathVariable("merketPlace") String merketPlace) {
		return orderRepository.findAll();
	}

}
