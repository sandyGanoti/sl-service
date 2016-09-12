package gr.di.uoa.sl.mvc.service;

import gr.di.uoa.sl.mvc.dao.ProductDao;
import gr.di.uoa.sl.pojos.Product;
import gr.di.uoa.sl.pojos.ProductShop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductManager {

	private ProductDao productDao = null;
	
	@Inject
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Transactional(readOnly=true)
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Transactional(readOnly=true)
	public Product getProductById(String productId) {
		return productDao.getProductById(productId);
	}
	
	@Transactional(readOnly=true)
	public List<Product> getProductsByShop(String shopId) {
		return productDao.getProductsByShop(shopId);
	}

	@Transactional(readOnly=true)
	public List<ProductShop> getShopsAndValueByProduct(String productId) {
		return productDao.getShopsAndValueByProduct(productId);
	}
	
	@Transactional(readOnly=true)
	public ProductShop getShopByProductWithMinimumValue(String productId) {
		return productDao.getShopByProductWithMinimumValue(productId);
	}
	
}