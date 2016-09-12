package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.pojos.Ratings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * RatingsDaoImpl uses JDBC based approach in order to interact
 * with the database (postgreSQL in this case)
 */
@Repository
public class RatingsDaoImpl implements RatingsDao {

	private final static Logger logger = Logger.getLogger(RatingsDaoImpl.class.getName());
	
	private DataSource dataSource = null;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Ratings getRatingsByListProductItemId(String listProductItemId) {
		ResultSet rs = null;
		Connection connection = null;
		Ratings ratings = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from ratings WHERE list_product_item_id=?::uuid");
			pst.setString(1, listProductItemId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ratings = new Ratings(rs.getString(1), rs.getInt(2), rs.getString(3));
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
		
		return ratings;
	}

	@Override
	public List<Ratings> getRatingsByShop(String shopId) {
		ResultSet rs = null;
		Connection connection = null;
		List<Ratings> ratings = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement pst = connection.prepareStatement("SELECT * FROM ratings WHERE list_product_item_id IN "+
			"(SELECT id from listproductitem WHERE shop_id=?::uuid AND completed=true and rated=true)");
			pst.setString(1, shopId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Ratings rating = new Ratings(rs.getString(1), rs.getInt(2), rs.getString(3));
				ratings.add(rating);
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

		return ratings;
	}

	@Override
	public void create(Ratings ratings) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			
			PreparedStatement pst = connection.prepareStatement("INSERT INTO ratings(list_product_item_id, value, note) VALUES(?, ?, ?)");
			pst.setString(1, ratings.getListProductItemId());
			pst.setInt(2, ratings.getValue());
			pst.setString(3, ratings.getNote());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}  finally {
			try {
				if (!(connection==null)) connection.close();
			} catch (SQLException e) {
				logger.info(e.getMessage());
			}
		}

	}

}