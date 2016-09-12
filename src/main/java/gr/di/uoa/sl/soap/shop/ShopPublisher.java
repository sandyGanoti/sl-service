package gr.di.uoa.sl.soap.shop;

import javax.xml.ws.Endpoint;

public class ShopPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/shop", new ShopImpl());
	}
	
}