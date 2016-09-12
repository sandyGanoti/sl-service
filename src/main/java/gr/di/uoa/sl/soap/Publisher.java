package gr.di.uoa.sl.soap;

import gr.di.uoa.sl.soap.product.ProductImpl;
import gr.di.uoa.sl.soap.shop.ShopImpl;

import javax.xml.ws.Endpoint;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Publisher /*implements ApplicationListener<ContextRefreshedEvent> */{

//	private static boolean initialized = false;
//	
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//		if (!initialized) {
//			initialized = true;
//			System.out.println("Publisher.main() run");
//			Endpoint.publish("http://localhost:9999/ws/product", new ProductImpl());
//			Endpoint.publish("http://localhost:9999/ws/shop", new ShopImpl());
//		}
//	}
//	
}