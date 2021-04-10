package com.example.luxasiaorderprocessor.mock.lazada;


import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import com.example.luxasiaorderprocessor.response.OrderDetails;
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
	}

	private void orders() throws IOException {
		mockServer
				.when(HttpRequest.request().withPath("/orders/get"))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										getOrderDetail()
										, MediaType.JSON_UTF_8)));
	}

	private OrdersResponse getOrderDetail() throws IOException {
		return objectMapper.readValue(new File(
				this.getClass().getClassLoader().getResource("getOrders.json").getFile()
		), OrdersResponse.class);
	}


}
