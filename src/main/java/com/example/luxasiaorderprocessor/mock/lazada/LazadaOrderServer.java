package com.example.luxasiaorderprocessor.mock.lazada;


import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import com.example.luxasiaorderprocessor.module.Order;
import com.example.luxasiaorderprocessor.response.OrderDetails;
import com.example.luxasiaorderprocessor.response.OrdersResponse;
import com.google.common.net.MediaType;
import java.util.ArrayList;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LazadaOrderServer implements CommandLineRunner {

	private ClientAndServer mockServer = startClientAndServer(8080);


	@Override
	public void run(String... args) throws Exception {
		orders();
	}

	private void orders() {
		mockServer
				.when(HttpRequest.request().withPath("/orders/get"))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										dummyOrder()
										, MediaType.JSON_UTF_8)));
	}

	private OrdersResponse dummyOrder() {
		return new OrdersResponse(new OrderDetails(1, new ArrayList<>() {{
			add(new Order("Aili - from Mock"));
		}}));
	}

}
