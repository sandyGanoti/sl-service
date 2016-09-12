package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.messenger.UserMessenger;
import gr.di.uoa.sl.pojos.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.cxf.transport.http.HttpBasicAuthSupplier.UserPass;
import org.springframework.stereotype.Repository;

@Repository
public class UserTableDaoImpl implements UserTableDao {

	private final static Logger logger = Logger.getLogger(UserTableDaoImpl.class.getName());

	private DataSource dataSource = null;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public User getUserById(String userId) {
		ResultSet rs = null;
		Connection connection = null;
		User user = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from usertable WHERE id=?::uuid");
			pst.setString(1, userId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
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
		
		return user;
	}

	@Override
	public User signUpUser(UserMessenger user) {
		Connection connection = null;
		User u = new User();
		u.setId(UUID.randomUUID().toString());
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("INSERT INTO usertable(id, username, password) VALUES (?::uuid, ?, ?);");
			pst.setString(1, u.getId());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getPassword());
			
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
		
		return u;
	}

	@Override
	public User getUserByNamePass(String userName, String password) {
		ResultSet rs = null;
		Connection connection = null;
		User user = null;
		try {
			connection = dataSource.getConnection();

			PreparedStatement pst = connection.prepareStatement("SELECT * from usertable WHERE username=? AND password=?");
			pst.setString(1, userName);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
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
		
		return user;
	}

}