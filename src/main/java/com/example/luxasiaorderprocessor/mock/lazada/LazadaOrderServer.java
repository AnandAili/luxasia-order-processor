package com.example.luxasiaorderprocessor.mock.lazada;


import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import com.example.luxasiaorderprocessor.mock.sapinventory.ProductInventory;
import com.example.luxasiaorderprocessor.mock.sapinventory.ProductStock;
import com.example.luxasiaorderprocessor.model.OrderDetail;
import com.example.luxasiaorderprocessor.response.OrderDetails;
import com.example.luxasiaorderprocessor.response.OrderItem;
import com.example.luxasiaorderprocessor.response.OrdersItemResponse;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LazadaOrderServer implements CommandLineRunner {

	private ClientAndServer mockServer = startClientAndServer(8080);

	private ObjectMapper objectMapper = new ObjectMapper();

  private Map<String, OrderDetail> orders = new HashMap<>();
  private Map<String, List<OrderItem>> orderItems = new HashMap<>();
  private Map<String, ProductStock> items = new HashMap<>();

  private static final String ORDER_1895464 = "1895464";
  private static final String ORDER_1895465 = "1895465";
  private static final String ORDER_1895464_ITEM_98109 = "98109";
  private static final String ORDER_1895464_ITEM_98108 = "98108";
  private static final String ORDER_1895465_ITEM_98119 = "98119";
  private static final String ORDER_1895465_ITEM_98118 = "98118";
  private static final int TOTAL_QUANTITY_98109 = 50;
  private static final int TOTAL_QUANTITY_98108 = 100;
	@Override
	public void run(String... args) throws Exception {

		loadOrderDetails();
		loadOrderItemDetails(ORDER_1895464);
		loadOrderItemDetails(ORDER_1895465);
		orders();
		orderItem(ORDER_1895464);
		orderItem(ORDER_1895465);
		updateProductQuantity(ORDER_1895464_ITEM_98109, TOTAL_QUANTITY_98109-1);
		updateProductQuantity(ORDER_1895464_ITEM_98108, TOTAL_QUANTITY_98108-1);
		updateProductQuantity(ORDER_1895465_ITEM_98119, TOTAL_QUANTITY_98109-2);
		updateProductQuantity(ORDER_1895465_ITEM_98118, TOTAL_QUANTITY_98108-2);
	}

	private void orders() throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("GET").withPath("/orders/get"))
				.callback( request -> {
					return new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										getOrderDetail()
										, MediaType.JSON_UTF_8));
				});
//				.respond(
//						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
//								.withBody(JsonBody.json(
//										getOrderDetail()
//										, MediaType.JSON_UTF_8)));
	}

	private void orderItem(String order_id) throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("GET").withPath("/order/items/get").withQueryStringParameter("order_id",order_id))
				.callback( httpRequest -> {
					return new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										getOrderItemDetails(order_id)
										, MediaType.JSON_UTF_8));
				});
//				.respond(
//						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
//								.withBody(JsonBody.json(
//										getOrderItemDetails(order_id)
//										, MediaType.JSON_UTF_8)));
	}

	private void updateProductQuantity(String item_id, int newQuantity) throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("POST").withPath("/product/price_quantity/update")
						.withQueryStringParameter("item_id",item_id)
				.withQueryStringParameter("quantity",String.valueOf(newQuantity))
				).callback( httpRequest -> {

			String itemIdFromQueryString = httpRequest.getQueryStringParameters().stream().filter( parameter -> parameter.getName().equals("item_id")).map(param -> param.getValues().get(0)).findFirst().get().getValue();
			String quantityFromQueryString = httpRequest.getQueryStringParameters().stream().filter( parameter -> parameter.getName().equals("quantity")).map(param -> param.getValues().get(0)).findFirst().get().getValue();
			String productName = orderItems.entrySet().stream().flatMap( entry -> entry.getValue().stream()).filter(orderitem -> orderitem.getOrder_item_id().equals(itemIdFromQueryString)).findFirst().get().getName();
			items.get(productName).setQuantity(Integer.parseInt(quantityFromQueryString));
			return new HttpResponse().withBody("Success")
								.withStatusCode(200);
		});
//				.respond(
//						new HttpResponse().withBody("Success")
//								.withStatusCode(200));
	}

	private OrdersResponse getOrderDetail() {
		OrdersResponse response = new OrdersResponse();
		response.setCode(200);
		response.setRequest_id(UUID.randomUUID().toString());
		List<OrderDetail> _orders = orders.entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
		response.setData(new OrderDetails(_orders.size(), _orders));
		return response;
	}

	private void loadOrderDetails() {
		OrdersResponse response = null;
		try {
			response =  objectMapper.readValue(new File(
					this.getClass().getClassLoader().getResource("getOrders.json").getFile()
			), OrdersResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.getData().getOrders().forEach( orderDetail -> {
			orderDetail.setStatuses("pending");
			orders.put(orderDetail.getOrder_id(), orderDetail);
		});
	}

	private OrdersItemResponse getOrderItemDetails(String order_id) {
		OrdersItemResponse response = new OrdersItemResponse();
		response.setCode(200);
		response.setRequest_id(UUID.randomUUID().toString());
		response.setData(orderItems.get(order_id));
		return response;
	}

	private void loadOrderItemDetails(String order_id)   {
		OrdersItemResponse response = null;
		try {
			response =  objectMapper.readValue(new File(
					this.getClass().getClassLoader().getResource("getOrderItemDetails" + order_id +".json").getFile()
			), OrdersItemResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		response.getData().forEach( orderItem -> {
			if(orderItems.containsKey(orderItem.getOrder_id())) {
				orderItems.get(orderItem.getOrder_id()).add(orderItem);
			} else {
				List<OrderItem> items = new ArrayList<>();
				items.add(orderItem);
				orderItems.put(orderItem.getOrder_id(), items);
			}

			if(!items.containsKey(orderItem.getName())) {
				items.put(orderItem.getName(), new ProductStock( orderItem.getName().equals(
						ProductInventory.SAMSUNG_MONITOR)? ProductInventory.STOCK_SAMSUNG_MONITOR: ProductInventory.STOCK_CALVIN_KIEN, orderItem.getName()));
			}
		});
	}


}
