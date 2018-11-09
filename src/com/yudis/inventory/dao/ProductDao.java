package com.yudis.inventory.dao;

import java.util.List;

import com.yudis.inventory.model.Product;

public interface ProductDao {
	int create(Product product);
	Product get(int id);
	List<Product> getAll();
	int update(Product product);
	int delete(int id);
}
