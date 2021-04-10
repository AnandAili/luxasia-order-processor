package com.example.luxasiaorderprocessor.resources;

import com.example.luxasiaorderprocessor.feignclient.OrderFeignClient;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuxasiaOrders {

	@Autowired
	OrderFeignClient orderFeignClient;

	@GetMapping("/orders/{merketPlace}") // orders/lazada
	public OrdersResponse getOrders(@PathVariable String merketPlace) {
		return orderFeignClient.getOrders();
		// check the stock
		//conform the order
		// update the stock in market
	}

}
