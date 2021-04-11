package com.example.luxasiaorderprocessor.mock.sapinventory;

import com.google.common.net.MediaType;
import java.util.HashMap;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.action.HttpCallbackActionHandler;
import org.mockserver.model.HttpClassCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductInventory  implements CommandLineRunner {

	public static final String SAMSUNG_MONITOR = "SamsungMonitor";
	public static final String CALVIN_KIEN = "calvinKien";
	public static final int STOCK_SAMSUNG_MONITOR = 100;
	public static final int STOCK_CALVIN_KIEN = 50;
	private ClientAndServer mockServer = ClientAndServer.startClientAndServer(8081);

  HashMap<String, Integer> warehouse = new HashMap() {{
  	put(SAMSUNG_MONITOR, STOCK_SAMSUNG_MONITOR);
  	put(CALVIN_KIEN, STOCK_CALVIN_KIEN);
	}};

	@Override
	public void run(String... args) throws Exception {
		setProductQuantity();
	}

	private void setProductQuantity() {
		getProduct(SAMSUNG_MONITOR, STOCK_SAMSUNG_MONITOR);
		getProduct(CALVIN_KIEN, STOCK_CALVIN_KIEN);
		updateProductQuantity(SAMSUNG_MONITOR,STOCK_SAMSUNG_MONITOR - 1);
		updateProductQuantity(CALVIN_KIEN,STOCK_CALVIN_KIEN - 1);
		updateProductQuantity(SAMSUNG_MONITOR,STOCK_SAMSUNG_MONITOR - 2);
		updateProductQuantity(CALVIN_KIEN,STOCK_CALVIN_KIEN -  2);
	}

	private void getProduct(String productName, int stock) {
		mockServer
				.when(HttpRequest.request().withPath("/inventory/products/" + productName))
				.callback( httpRequest -> {
					return new HttpResponse()
							.updateHeader("Content-Type", "application/json")
							.withBody(JsonBody.json(
									new ProductStock(warehouse.get(httpRequest.getPath().getValue().contains(SAMSUNG_MONITOR)? SAMSUNG_MONITOR: CALVIN_KIEN),
											httpRequest.getPath().getValue().contains(SAMSUNG_MONITOR)? SAMSUNG_MONITOR: CALVIN_KIEN)
									, MediaType.JSON_UTF_8));
				});
//				.respond(
//						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200)
//								.withBody(JsonBody.json(
//										new ProductStock(stock, productName)
//										, MediaType.JSON_UTF_8)));
	}

	private void updateProductQuantity(String productName, long quantity) {
		mockServer
				.when(HttpRequest.request().withMethod("PUT").withPath("/inventory/products")
						.withQueryStringParameter("productName",productName)
						.withQueryStringParameter("quantity", String.valueOf(quantity)))
				.callback( httpRequest -> {
					String prodNameFromQueryString = httpRequest.getQueryStringParameters().stream().filter( parameter -> parameter.getName().equals("productName")).map(param -> param.getValues().get(0)).findFirst().get().getValue();

					warehouse.put(prodNameFromQueryString, warehouse.get(prodNameFromQueryString) - 1);
					return new HttpResponse()
							.updateHeader("Content-Type", "application/json")
							.withStatusCode(200);
				});

//				.respond(
//						new HttpResponse().updateHeader("Content-Type", "application/json").withStatusCode(200));
	}
}
