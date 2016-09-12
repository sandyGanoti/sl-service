package gr.di.uoa.sl.mvc.service;

import gr.di.uoa.sl.messenger.UserMessenger;
import gr.di.uoa.sl.mvc.dao.UserTableDao;
import gr.di.uoa.sl.pojos.User;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTableManager {

	private UserTableDao userTableDao = null;
	
	@Inject
	public void setUserTableDao(UserTableDao userTableDao) {
		this.userTableDao = userTableDao;
	}
	
	@Transactional(readOnly=true)
	public User authenticateUser(String userId) {
		return userTableDao.getUserById(userId);
	}
	
	@Transactional(readOnly=true)
	public User authenticateUser(String userName, String password) {
		return userTableDao.getUserByNamePass(userName, password);
	}

	
	
	@Transactional(rollbackFor={Exception.class})
	public User signUpUser(UserMessenger user) {
		return userTableDao.signUpUser(user);
		
	}
	
}