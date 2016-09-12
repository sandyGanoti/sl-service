package gr.di.uoa.sl.mvc.dao;

import gr.di.uoa.sl.messenger.UserMessenger;
import gr.di.uoa.sl.pojos.User;

public interface UserTableDao {

	public User getUserById(String userId);
	
	public User getUserByNamePass(String userName, String password);
	
	public User signUpUser(UserMessenger user);
	
}