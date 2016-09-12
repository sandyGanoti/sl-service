package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.pojos.Product;
import gr.di.uoa.sl.pojos.ProductShop;

import java.util.List;

public interface ProductDao {

	public List<Product> getProducts();

	public Product getProductById(String productId);
	
	public List<Product> getProductsByShop(String shopId);

	public List<ProductShop> getShopsAndValueByProduct(String productId);
	
	public ProductShop getShopByProductWithMinimumValue(String productId);
	
}