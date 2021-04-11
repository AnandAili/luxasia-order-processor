package com.example.luxasiaorderprocessor.resources;

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
public class LuxasiaOrders {

	@Autowired
	OrderFeignClient orderFeignClient;

	@Autowired
	ProductStockFeignClient productStockFeignClient;

	@Autowired
	OrderRepository orderRepository;

 // schedular (2 min)

	@GetMapping("/orders/{merketPlace}") // orders/lazada
	public List<OrderDetail> getOrders(@PathVariable String merketPlace) {
		//1. TODO: get all orders from lazada   -- /orders/get (mocked)
		OrdersResponse ordersResponse = orderFeignClient.getOrders();

		//2. TODO: store the order in our database  -- our database
		ordersResponse.getData().getOrders().forEach( order -> {
			orderRepository.save(order);
		});

		//3. TODO: get Product Quantity from SAP MOCK System -- /inventroy/products/ProdctA
		ordersResponse.getData().getOrders().forEach( order -> {
			OrdersItemResponse ordersItemResponse = orderFeignClient.getOrderItem(order.getOrder_id());
			// TODO: store the orderItem as well
			ordersItemResponse.getData().forEach( orderItem -> {
				ProductStock productStock = productStockFeignClient.getProductQuantities(orderItem.getName());
				if(productStock.getQuantity() != 0) {
					//5. TODO: decrement the product  quantity in SAP Inventory
					// 1. need mock the SAP Inventory call
					int newQuantity = productStock.getQuantity() - 1;
					productStockFeignClient.updateProductStock(orderItem.getName(),
							newQuantity);

					//6. TODO: update the available product quantity in lazada market place
					// 6.1. need to mock setProductQuantity
					orderFeignClient.updateProductQuantity(orderItem.getOrder_item_id(),
							String.valueOf(newQuantity));
				} else {
					//TODO: cancel the order
				}
			});
		});






    // ------
    // 7. TODO: Update the ORder with ORDER_STATUS (SHIPPED)
		// mock Update order status lazada call


		return orderRepository.findAll();
	}

}
