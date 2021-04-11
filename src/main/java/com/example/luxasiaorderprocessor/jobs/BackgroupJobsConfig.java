package com.example.luxasiaorderprocessor.jobs;

import com.example.luxasiaorderprocessor.feignclient.OrderFeignClient;
import com.example.luxasiaorderprocessor.feignclient.ProductStockFeignClient;
import com.example.luxasiaorderprocessor.mock.sapinventory.ProductStock;
import com.example.luxasiaorderprocessor.repository.OrderRepository;
import com.example.luxasiaorderprocessor.response.OrdersItemResponse;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BackgroupJobsConfig {

	@Autowired
	OrderFeignClient orderFeignClient;

	@Autowired
	ProductStockFeignClient productStockFeignClient;

	@Autowired
	OrderRepository orderRepository;

	@Scheduled(fixedDelay =60000)
	public void getOrdersFromLazada() {
		System.out.println("Process new Orders");
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

//					try {
//						Thread.sleep(60000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
					// TODO: update status to ready to SHIP
				} else {
					//TODO: cancel the order
				}
			});
		});







	}

	@Scheduled(fixedDelay = 120000)
	public void updateOrderStatusToShipped() {
		System.out.println("Update status to shipped");
		// ------
		// 7. TODO: Update the ORder with ORDER_STATUS (SHIPPED)
		// mock Update order status lazada call
	}
}
