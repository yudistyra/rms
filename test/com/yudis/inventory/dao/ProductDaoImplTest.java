package com.yudis.inventory.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;

import com.yudis.inventory.model.Product;

public class ProductDaoImplTest {

	private int result;
	
	@Test
	public void createShouldCreateAProduct() {
		ProductDaoImpl dao = ProductDaoImpl.getInstance();
		Product product = new Product();
		
		product.setName("Iphone");
		product.setDescription("Awesome");
		product.setPrice(800);
		
		result = dao.create(product);
		
		assertNotNull(result);
		assertNotEquals(0, result);
	}

}
