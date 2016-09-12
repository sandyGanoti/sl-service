package gr.di.uoa.sl.test;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import gr.di.uoa.sl.mvc.service.ProductManager;
import gr.di.uoa.sl.pojos.Product;
import gr.di.uoa.sl.pojos.ProductShop;

@Service
public class Demo {
	
	ProductManager pm = null;

	@Inject
	public void setProductManager(ProductManager productManager) {
		this.pm = productManager;
	}
	
	public static void main(String[] args) {
		
		
//		List<ProductEntity> l =  pm.getProductsByShop("33a70b61-9f8a-4bc7-8402-091e8e6c7618");
//		for (ProductEntity pe: l)
//			System.out.println(pe.getName());
//		
//		
//		ProductShopEntity pse = pm.getShopByProductWithMinimumValue("3ff70b61-9f8a-4bc7-8402-091e8e6c7618");
//		System.out.println(pse.getName() + " has the minimum value ( "+ pse.getValue() + " )");
//		
//		List<ProductShopEntity> list = pm.getShopsAndValueByProduct("3ff70b61-9f8a-4bc7-8402-091e8e6c7618");
//		for (ProductShopEntity pp: list)
//			System.out.println(pp.getName() +  " - " + pp.getValue());
	}
	
	
}