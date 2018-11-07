package com.yudis.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.yudis.inventory.model.Product;

public class ProductDaoImpl implements ProductDao {
	
	private DataSource dataSource;
	
	public ProductDaoImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void create(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product get(int id) {
		Product product = new Product();
		String sql = "select * from product where id = ?";
		ResultSet rs = null;
		
		try ( 
				Connection con = dataSource.getConnection();
				PreparedStatement p = con.prepareStatement(sql);
				) {
			
			p.setString(1, Integer.toString(id));
			rs = p.executeQuery();
			
			while (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));
				
				if(rs.getInt("active") == 1 )
					product.setActive(true);
				else
					product.setActive(false);
			}
			
		} catch (Exception e) {
			System.out.println("SQL Error " + e.getMessage());
		}
		
		return product;
	}

	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		String sql = "select * from product";
		
		try (
				Connection con = dataSource.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)
				) {
			
			while( rs.next() ) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String desc = rs.getString("description");
				int price = rs.getInt("price");
				boolean active = false;
				
				if(rs.getInt("active") == 1) {
					active = true;
				}
				
				Product temp = new Product(id, name, desc, price, active);
				products.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("SQL error " + e.getMessage());
		}
		return products;
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
