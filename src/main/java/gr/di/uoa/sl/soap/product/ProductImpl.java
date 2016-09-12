package gr.di.uoa.sl.soap.product;

import gr.di.uoa.sl.mvc.service.ProductManager;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface="gr.di.uoa.sl.soap.product.Product")
public class ProductImpl implements Product {

	ProductManager productManager;
	
	@Inject
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}


	@Override
	public List<gr.di.uoa.sl.pojos.Product> getProduct() {
		return productManager.getProducts();
	}

}