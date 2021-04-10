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
		getProduct("Bean Rester Dooby Red",100);
		getProduct("calvinKien",50);
		updateProductQuantity("Bean Rester Dooby Red",99);
		updateProductQuantity("calvinKien",49);
	}

	private void getProduct(String productName, int stock) {
		mockServer
				.when(HttpRequest.request().withPath("/inventory/products/" + productName))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
								.withBody(JsonBody.json(
										new ProductStock(stock, productName)
										, MediaType.JSON_UTF_8)));
	}

	private void updateProductQuantity(String productName, long quantity) {
		mockServer
				.when(HttpRequest.request().withMethod("PUT").withPath("/inventory/products")
						.withQueryStringParameter("productName",productName)
						.withQueryStringParameter("newQuantity", String.valueOf(quantity)))
				.respond(
						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200));
	}
}
