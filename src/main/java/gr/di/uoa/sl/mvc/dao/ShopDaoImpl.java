package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.pojos.Shop;

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
public class ShopDaoImpl implements ShopDao {
	
	private final static Logger logger = Logger.getLogger(ShopDaoImpl.class.getName());
	
	private DataSource dataSource = null;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Shop> getShops() {
		ResultSet rs = null;
		Connection connection = null;
		List<Shop> shops = new ArrayList<Shop>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from shop");

			rs = pst.executeQuery();
			while(rs.next()) {
				Shop shop = new Shop(rs.getString(1), rs.getString(2));
				shops.add(shop);
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
		
		return shops;
	}
	
	@Override
	public Shop getShopById(String shopId) {
		ResultSet rs = null;
		Connection connection = null;
		Shop shop = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from shop  WHERE shop_id=?::uuid");
			pst.setString(1, shopId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				shop = new Shop(rs.getString(1), rs.getString(2));
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
		
		return shop;
	}

	@Override
	public void getShopDetails(String shopId) {
		ResultSet rs = null;
		Connection connection = null;
		List<Shop> shops = new ArrayList<Shop>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from shop");

			rs = pst.executeQuery();
			while(rs.next()) {
				Shop shop = new Shop(rs.getString(1), rs.getString(2));
				shops.add(shop);
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
	}


	
}