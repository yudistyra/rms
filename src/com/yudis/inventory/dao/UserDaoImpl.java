package com.yudis.inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.yudis.inventory.model.User;

public class UserDaoImpl implements UserDao {

	private DataSource dataSource;

	public UserDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void create(User user) {
		String sql = "insert into user (first_name,last_name,email) values (?,?,?)";

		try (
				Connection conn = dataSource.getConnection(); 
				PreparedStatement p = conn.prepareStatement(sql);
		) {
			p.setString(1, user.getUsername());
			p.setString(2, user.getPassword());
			p.setString(3, user.getFullname());
			p.setString(4, user.getEmail());

			p.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL Error " + e.getMessage());
		}
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		String sql = "Select * from user";
		
		try ( Connection conn = dataSource.getConnection();
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(sql)) {
			while(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				
				User list = new User(id, username, fullname, email);
				
				users.add(list);
			}
		} catch (SQLException e) {
			System.out.println("SQL error " + e.getMessage());
		}
		
		return users;
	}
	
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(int id, User user) {
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
