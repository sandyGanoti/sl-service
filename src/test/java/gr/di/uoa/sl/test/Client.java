package gr.di.uoa.sl.test;

import java.net.URL;

import gr.di.uoa.sl.soap.product.Product;
import gr.di.uoa.sl.soap.shop.Shop;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class Client {

	public static void main(String[] args) throws Exception {
	
		URL url1 = new URL("http://localhost:9999/ws/shop?wsdl");
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname1 = new QName("http://shop.soap.sl.uoa.di.gr/", "ShopImplService");
        Service service1 = Service.create(url1, qname1);
        Shop hello1 = service1.getPort(Shop.class);
        System.out.println(hello1.getShop());
        
        
//		URL url11 = new URL("http://localhost:9999/ws/product?wsdl");
//        //1st argument service URI, refer to wsdl document above
//		//2nd argument is service name, refer to wsdl document above
//        QName qname11 = new QName("http://product.soap.sl.uoa.di.gr/", "ProductImplService");
//        Service service11 = Service.create(url11, qname11);
//        Product hello11 = service11.getPort(Product.class);
//        System.out.println(hello11.getProduct());
        
	}
	
}