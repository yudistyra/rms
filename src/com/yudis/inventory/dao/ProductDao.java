package com.yudis.inventory.dao;

import java.util.List;

import com.yudis.inventory.model.Product;

public interface ProductDao {
	void create(Product product);
	Product get(int id);
	List<Product> getAll();
	void update(Product product);
	void delete(int id);
}
