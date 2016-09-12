package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.messenger.BuyItemFromShoppingList;
import gr.di.uoa.sl.messenger.NewProductToList;
import gr.di.uoa.sl.pojos.ListProductItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class ListProductDaoImpl implements ListProductDao {
	
	private final static Logger logger = Logger.getLogger(ListProductDaoImpl.class.getName());

	private DataSource dataSource = null;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<ListProductItem> getShoppingListByUser(String userId) {
		ResultSet rs = null;
		Connection connection = null;
		List<ListProductItem> listProductItem = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from listproductitem WHERE user_id=?::uuid");
			pst.setString(1, userId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ListProductItem lpe = new ListProductItem(rs.getString(1), rs.getString(2), rs.getString(3),
														  rs.getString(4), rs.getBoolean(5), rs.getBoolean(6),
														  rs.getInt(7));
				listProductItem.add(lpe);
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
		
		return listProductItem;
	}
	
	@Override
	public List<ListProductItem> getShoppingListByShop(String shopId) {
		ResultSet rs = null;
		Connection connection = null;
		List<ListProductItem> listProductItem = new ArrayList<ListProductItem>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from listproductitem WHERE shop_id=?::uuid");
			pst.setString(1, shopId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ListProductItem lpe = new ListProductItem(rs.getString(1), rs.getString(2), rs.getString(3),
						  rs.getString(4), rs.getBoolean(5), rs.getBoolean(6),
						  rs.getInt(7));
				listProductItem.add(lpe);
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
		
		return listProductItem;
	}
	
	@Override
	public List<ListProductItem> getShoppingListByProduct(String productId) {
		ResultSet rs = null;
		Connection connection = null;
		List<ListProductItem> listProductItem = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from listproductitem WHERE product_id=?::uuid");
			pst.setString(1, productId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ListProductItem lpe = new ListProductItem(rs.getString(1), rs.getString(2), rs.getString(3),
						  rs.getString(4), rs.getBoolean(5), rs.getBoolean(6),
						  rs.getInt(7));
				listProductItem.add(lpe);
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
		
		return listProductItem;
	}
	
	@Override
	public List<ListProductItem> getShoppingListByUserAndProduct(String userId, String productId) {
		ResultSet rs = null;
		Connection connection = null;
		List<ListProductItem> listProductItem = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from listproductitem WHERE user_id=?::uuid AND product_id=?::uuid");
			pst.setString(1, userId);
			pst.setString(2, productId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ListProductItem lpe = new ListProductItem(rs.getString(1), rs.getString(2), rs.getString(3),
						  rs.getString(4), rs.getBoolean(5), rs.getBoolean(6),
						  rs.getInt(7));
				listProductItem.add(lpe);
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
		
		return listProductItem;
	}
	
	@Override
	public List<ListProductItem> getShoppingListByUserAndShop(String userId, String shopId) {
		ResultSet rs = null;
		Connection connection = null;
		List<ListProductItem> listProductItem = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from listproductitem WHERE user_id=?::uuid AND shop_id=?::uuid");
			pst.setString(1, userId);
			pst.setString(2, shopId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ListProductItem lpe = new ListProductItem(rs.getString(1), rs.getString(2), rs.getString(3),
						  rs.getString(4), rs.getBoolean(5), rs.getBoolean(6),
						  rs.getInt(7));
				listProductItem.add(lpe);
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
		
		return listProductItem;
	}
	
	@Override
	public ListProductItem getListProductItemByUserAndProductAndShop(String userId, String shopId, String productId) {
		ResultSet rs = null;
		Connection connection = null;
		ListProductItem listProductItem = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from listproductitem WHERE user_id=?::uuid AND shop_id=?::uuid AND product_id=?::uuid");
			pst.setString(1, userId);
			pst.setString(2, shopId);
			pst.setString(3, productId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				listProductItem = new ListProductItem(rs.getString(1), rs.getString(2), rs.getString(3),
						  rs.getString(4), rs.getBoolean(5), rs.getBoolean(6),
						  rs.getInt(7));
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
		
		return listProductItem;
	}

	@Override
	public void addProductToShoppingList(NewProductToList newProductToList) 
														throws SQLException {
		Connection connection = null;
		
		connection = dataSource.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(
		"INSERT INTO listproductitem(id, user_id, shop_id, product_id, completed, rated, real_value) VALUES(?::uuid, ?::uuid, ?::uuid, ?::uuid, ?, ?, ?)");
		pst.setString(1, UUID.randomUUID().toString());
		pst.setString(2, newProductToList.getUserToken());
		pst.setString(3, null);
		pst.setString(4, newProductToList.getProductId());
		pst.setBoolean(5, false);
		pst.setBoolean(6, false);
		pst.setInt(7, -1);
			
		pst.executeUpdate();
		
		try {
			if (!(connection==null)) connection.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public void removeProductFromShoppingList(NewProductToList newProductToList) 
														throws SQLException {
		Connection connection = null;
		
		connection = dataSource.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(
		"DELETE FROM listproductitem where user_id=?::uuid AND product_id=?::uuid");
		pst.setString(1, newProductToList.getUserToken());
		pst.setString(2, newProductToList.getProductId());

		pst.executeUpdate();
		
		try {
			if (!(connection==null)) connection.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	
	@Override
	public void buyItemFromShoppingList(BuyItemFromShoppingList buyItemFromShoppingList, String listProductItemId)
																	throws SQLException {
		Connection connection = null;
		
		connection = dataSource.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(
		"UPDATE listProductItem set shop_id=?::uuid, completed=?, real_value=? WHERE id=?::uuid");
		pst.setString(1, buyItemFromShoppingList.getShopId());
		pst.setBoolean(2, true);
		pst.setInt(3, buyItemFromShoppingList.getValue());
		pst.setString(4, listProductItemId);
		
		pst.executeUpdate();
		
		try {
			if (!(connection==null)) connection.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}	
	}

	@Override
	public void rateItemFromShoppingList(ListProductItem listProductItem) throws SQLException {
		Connection connection = null;
		
		connection = dataSource.getConnection();
		
		PreparedStatement pst = connection.prepareStatement(
		"UPDATE listProductItem set rated=? WHERE id=?::uuid");
		pst.setBoolean(1, true);
		pst.setString(2, listProductItem.getId());
		
		pst.executeUpdate();
		try {
			if (!(connection==null)) connection.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}	
	}
	
}