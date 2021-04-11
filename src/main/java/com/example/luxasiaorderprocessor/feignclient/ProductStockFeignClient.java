package com.example.luxasiaorderprocessor.feignclient;

import com.example.luxasiaorderprocessor.mock.sapinventory.ProductStock;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-quantity", url = "http://localhost:8081/", configuration = EnableFeignConfiguration.class)
public interface ProductStockFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/inventory/products/{productName}")
	ProductStock getProductQuantities(@PathVariable("productName") String productName);

	@PutMapping("/inventory/products")
	void updateProductStock(@RequestParam("productName") String productName, @RequestParam("quantity") long quantity);

}
