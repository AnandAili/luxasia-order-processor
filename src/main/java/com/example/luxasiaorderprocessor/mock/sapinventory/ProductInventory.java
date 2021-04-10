package com.example.luxasiaorderprocessor.mock.sapinventory;

import com.google.common.net.MediaType;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductInventory  implements CommandLineRunner {

	private ClientAndServer mockServer = ClientAndServer.startClientAndServer(8081);

	@Override
	public void run(String... args) throws Exception {
		setProductQuantity();
	}

	private void setProductQuantity() {
		mockServer
				.when(HttpRequest.request().withPath("/inventory/products/ProductA"))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										new ProductStock(2, "ProductA")
										, MediaType.JSON_UTF_8)));
	}
}
