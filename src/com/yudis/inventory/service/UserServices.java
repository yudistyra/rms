package com.yudis.inventory.service;

import java.util.List;

import com.yudis.inventory.dao.UserDaoImpl;
import com.yudis.inventory.model.User;

public class UserServices {
	private UserDaoImpl userModel;
	private static UserServices instance;

	private UserServices() {
		this.userModel = UserDaoImpl.getInstance();
	}
	
	public static UserServices getInstance() {
		if(instance == null) {
			instance = new UserServices();
		}
		
		return instance;
	}
	
	public User login(String username, String password) {
		User user = null;
		
		user = userModel.getByUsername(username);
		
		if(password.equals(user.getPassword()) && user.isActive()) {
			return user;
		}
		
		return null;
	}
	
	public List<User> getAll() {
		return userModel.getAll();
	}
	
	public int getCountUser() {
		return userModel.getAll().size();
	}
	
	public String register(User newuser, String confirmpassword) {
		
		if(newuser.getUsername().isEmpty() || newuser.getPassword().isEmpty() || newuser.getFullname().isEmpty()) {
			return "Please fill required fields";
		}
		else {
			if(newuser.getPassword().equals(confirmpassword)) {
				User user = new User (
						newuser.getUsername(),
						newuser.getPassword(),
						newuser.getFullname(),
						newuser.getEmail()
					);
		
				int result = userModel.create(user);
				
				if(result > 0) {
					return "success";
				}
			}
			else {
				return "Password and Confirm Password don't match";
			}
		}
		
		return "Error";
			
	}
}
