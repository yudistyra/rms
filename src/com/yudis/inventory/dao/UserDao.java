package com.yudis.inventory.dao;

import java.util.List;

import com.yudis.inventory.model.User;

public interface UserDao {
	int create(User user);
	List<User> getAll();
	User getById(int id);
	User getByUsername(String username);
	int update(int id, User user);
	int delete(int id);
}
