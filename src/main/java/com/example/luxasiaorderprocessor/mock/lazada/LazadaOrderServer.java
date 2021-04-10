package com.example.luxasiaorderprocessor.mock.lazada;


import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import com.example.luxasiaorderprocessor.model.OrderDetail;
import com.example.luxasiaorderprocessor.response.OrderDetails;
import com.example.luxasiaorderprocessor.response.OrdersItemResponse;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.MediaType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class LazadaOrderServer implements CommandLineRunner {

	private ClientAndServer mockServer = startClientAndServer(8080);

	private ObjectMapper objectMapper = new ObjectMapper();


	@Override
	public void run(String... args) throws Exception {
		orders();
		orderItem();
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

	private void orderItem() throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("GET").withPath("/order/items/get").withQueryStringParameter("order_id","31202"))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										getOrderItemDetail()
										, MediaType.JSON_UTF_8)));
	}

	private void updateProductQuantity() throws IOException {
		mockServer
				.when(HttpRequest.request().withMethod("GET").withPath("/product/price_quantity/update")
						.withQueryStringParameter("item_id","31202")
				.withQueryStringParameter("quantity","99"))
				.respond(
						new HttpResponse()
								.withStatusCode(200));
	}

	private OrdersResponse getOrderDetail() throws IOException {
		return objectMapper.readValue(new File(
				this.getClass().getClassLoader().getResource("getOrders.json").getFile()
		), OrdersResponse.class);
	}

	private OrdersItemResponse getOrderItemDetail() throws IOException {
		return objectMapper.readValue(new File(
				this.getClass().getClassLoader().getResource("getOrderItemDetails.json").getFile()
		), OrdersItemResponse.class);
	}


}
