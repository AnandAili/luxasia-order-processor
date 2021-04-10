package com.example.luxasiaorderprocessor.resources;

import com.example.luxasiaorderprocessor.feignclient.OrderFeignClient;
import com.example.luxasiaorderprocessor.feignclient.ProductStockFeignClient;
import com.example.luxasiaorderprocessor.mock.sapinventory.ProductStock;
import com.example.luxasiaorderprocessor.repository.OrderRepository;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuxasiaOrders {

	@Autowired
	OrderFeignClient orderFeignClient;

	@Autowired
	ProductStockFeignClient productStockFeignClient;

	@Autowired
	OrderRepository orderRepository;



	@GetMapping("/orders/{merketPlace}/products/{productName}") // orders/lazada
	public OrdersResponse getOrders(@PathVariable String merketPlace, @PathVariable String productName) {
		OrdersResponse ordersResponse =  orderFeignClient.getOrders();
		// check the stock
		//conform the order
		// update the stock in market
		if(productStockFeignClient.getProductQuantities(productName).getQuantity() != 0) {
			ordersResponse.getData().getOrders().forEach( order -> orderRepository.save(order));
		}

		return ordersResponse;
	}

}
