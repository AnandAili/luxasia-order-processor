package com.example.luxasiaorderprocessor.feignclient;

import com.example.luxasiaorderprocessor.mock.sapinventory.ProductStock;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-quantity", url = "http://localhost:8081/", configuration = EnableFeignConfiguration.class)
public interface ProductStockFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventory/products/{productName}")
	ProductStock getProductQuantities(@PathVariable String productName);
}
