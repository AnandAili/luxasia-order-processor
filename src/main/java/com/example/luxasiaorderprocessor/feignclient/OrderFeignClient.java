package com.example.luxasiaorderprocessor.feignclient;

import com.example.luxasiaorderprocessor.response.OrdersItemResponse;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orders", url = "http://localhost:8080/", configuration = EnableFeignConfiguration.class)
public interface OrderFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/orders/get")
	OrdersResponse getOrders();

	@RequestMapping(method = RequestMethod.GET, value = "/order/items/get")
	OrdersItemResponse getOrderItem(@RequestParam String order_id);

	@PostMapping("/product/price_quantity/update")
	void updateProductQuantity(@RequestParam String item_id, @RequestParam String quantity);



}
