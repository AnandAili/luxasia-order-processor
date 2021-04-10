package com.example.luxasiaorderprocessor.feignclient;

import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "orders", url = "http://localhost:8080/", configuration = EnableFeignConfiguration.class)
public interface OrderFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/orders/get")
	OrdersResponse getOrders();

}
