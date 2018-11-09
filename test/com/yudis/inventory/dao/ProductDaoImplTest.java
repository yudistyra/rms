package com.yudis.inventory.dao;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;

import com.yudis.inventory.model.Product;

public class ProductDaoImplTest {

	private int result;
	
	@Resource(name = "jdbc/inventory")
	private DataSource dataSource;
	
	@Test
	public void createShouldCreateAProduct() {
		ProductDaoImpl dao = new ProductDaoImpl(dataSource);
		Product product = new Product();
		
		product.setName("Iphone");
		product.setDescription("Awesome");
		product.setPrice(800);
		
		result = dao.create(product);
		
		assertNotNull(result);
		Assert.assertNotEquals(0, result);
	}

}
