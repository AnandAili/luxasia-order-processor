package com.example.luxasiaorderprocessor.mock.lazada;


import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import com.example.luxasiaorderprocessor.model.OrderDetail;
import com.example.luxasiaorderprocessor.response.OrdersItemResponse;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.List;
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

  private List<OrderDetail> orders = getOrderDetail().getData().getOrders();
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
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										getOrderDetail()
										, MediaType.JSON_UTF_8)));
	}

	private void orderItem(String order_id) throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("GET").withPath("/order/items/get").withQueryStringParameter("order_id",order_id))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										getOrderItemDetails(order_id)
										, MediaType.JSON_UTF_8)));
	}

	private void updateProductQuantity(String item_id, int newQuantity) throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("POST").withPath("/product/price_quantity/update")
						.withQueryStringParameter("item_id",item_id)
				.withQueryStringParameter("quantity",String.valueOf(newQuantity))
				)
				.respond(
						new HttpResponse().withBody("Success")
								.withStatusCode(200));
	}

	private OrdersResponse getOrderDetail() {
		OrdersResponse response = null;
		try {
			response =  objectMapper.readValue(new File(
					this.getClass().getClassLoader().getResource("getOrders.json").getFile()
			), OrdersResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private OrdersItemResponse getOrderItemDetails(String order_id) throws IOException {
		return objectMapper.readValue(new File(
				this.getClass().getClassLoader().getResource("getOrderItemDetails" + order_id +".json").getFile()
		), OrdersItemResponse.class);
	}


}
