package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.pojos.Product;
import gr.di.uoa.sl.pojos.ProductShop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

	private final static Logger logger = Logger.getLogger(ProductDaoImpl.class.getName());
	
	private DataSource dataSource = null;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Product> getProducts() {
		ResultSet rs = null;
		Connection connection = null;
		List<Product> products = new ArrayList<Product>();
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement pst = connection.prepareStatement("SELECT * from product");
			
			rs = pst.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getString(1), rs.getString(2));
				products.add(product);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		} finally {
			try {
				if (!(connection==null)) connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		
		return products;
	}
	
	@Override
	public Product getProductById(String productId) {
		ResultSet rs = null;
		Connection connection = null;
		Product product = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from product WHERE product_id=?::uuid");
			pst.setString(1, productId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				product = new Product(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}  finally {
			try {
				if (!(connection==null)) connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		
		return product;
	}
	
	@Override
	public List<Product> getProductsByShop(String shopId) {
		ResultSet rs = null;
		Connection connection = null;
		List<Product> products = new ArrayList<Product>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from products_shop  WHERE shop_id=?::uuid");
			pst.setString(1, shopId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product product = new Product(rs.getString(1), rs.getString(2));
				products.add(product);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}  finally {
			try {
				if (!(connection==null)) connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		
		return products;
	}

	@Override
	public List<ProductShop> getShopsAndValueByProduct(String productId) {
		ResultSet rs = null;
		Connection connection = null;
		List<ProductShop> productShop = new ArrayList<ProductShop>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from products_shop  WHERE product_id=?::uuid");
			pst.setString(1, productId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ProductShop product = new ProductShop(rs.getString(1), rs.getString(2), Integer.parseInt(rs.getString(3)));
				productShop.add(product);
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}  finally {
			try {
				if (!(connection==null)) connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		
		return productShop;
	}
	
	@Override
	public ProductShop getShopByProductWithMinimumValue(String productId) {
		ResultSet rs = null;
		Connection connection = null;
		ProductShop productShop = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * FROM products_shop WHERE value = (SELECT min(value) from products_shop) and product_id=?::uuid;");
			pst.setString(1, productId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				productShop = new ProductShop(rs.getString(1), rs.getString(2), Integer.parseInt(rs.getString(3)));
			}
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}  finally {
			try {
				if (!(connection==null)) connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}
		
		return productShop;
	}
}