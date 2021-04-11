package com.example.luxasiaorderprocessor.controller;

import com.example.luxasiaorderprocessor.feignclient.OrderFeignClient;
import com.example.luxasiaorderprocessor.feignclient.ProductStockFeignClient;
import com.example.luxasiaorderprocessor.mock.sapinventory.ProductStock;
import com.example.luxasiaorderprocessor.model.OrderDetail;
import com.example.luxasiaorderprocessor.repository.OrderRepository;
import com.example.luxasiaorderprocessor.response.OrdersItemResponse;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuxasiaOrdersController {

	@Autowired
	OrderRepository orderRepository;


	@GetMapping("/orders/{merketPlace}") // orders/lazada
	public List<OrderDetail> getOrders(@PathVariable String merketPlace) {
		return orderRepository.findAll();
	}

}
