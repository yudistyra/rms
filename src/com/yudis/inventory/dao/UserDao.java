package com.yudis.inventory.dao;

import java.util.List;

import com.yudis.inventory.model.User;

public interface UserDao {
	void create(User user);
	List<User> getAll();
	User getById(int id);
	User getByUsername(String username);
	void update(int id, User user);
	void delete(int id);
}
