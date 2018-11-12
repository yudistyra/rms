package com.yudis.inventory.service;

import java.util.List;

import com.yudis.inventory.dao.ProductDaoImpl;
import com.yudis.inventory.model.Product;

public class ProductServices {
	
	private ProductDaoImpl productModel;
	
	public ProductServices() {
		productModel = ProductDaoImpl.getInstance();
	}

	public List<Product> getAll() {
		return productModel.getAll();
	}
	
	public Product get(int id) {
		return productModel.get(id);
	}
	
	public String create(String name, String desc, int price) {
		Product newproduct = new Product();
	
		if(name.isEmpty()) {
			return "Name is required";
		}
		else if(price <= 0) {
			return "Price must have a value more than 0";
		}
		else {
			newproduct.setName(name);
			newproduct.setDescription(desc);
			newproduct.setPrice(price);
			
			int res = productModel.create(newproduct);
			
			if(res > 0) {
				return "success";
			}
			else {
				return "Failed to create a product";
			}
		}
	}
	
	public String update(Product product) {
		if(product.getName().isEmpty()) {
			return "Name is required";
		}
		else if(product.getPrice() <= 0) {
			return "Price must have a value more than 0";
		}
		else {
			int res = productModel.update(product);
			
			if(res > 0) {
				return "success";
			}
			else {
				return "Failed to update product";
			}
		}
	}
	
	public String delete(int id) {
		if(productModel.delete(id) > 0) {
			return "success";
		}
		else {
			return "Failed to delete product";
		}
	}
}
