package edu.fra.uas.user.service;

import edu.fra.uas.user.model.User;

public interface IUserService {

	public Boolean loginUser(String email, String password);
	
	public void createUser(User user);
	
	public User deleteUser(long userId);
	

	
	public User getUser(long userId);
	
	public User assignRole(long UserId);
	

}
