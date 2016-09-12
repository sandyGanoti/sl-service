package gr.di.uoa.sl.soap.product;

import javax.xml.ws.Endpoint;

public class ProductPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/product", new ProductImpl());
	}
	
}