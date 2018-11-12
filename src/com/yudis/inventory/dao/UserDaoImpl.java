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
import com.yudis.inventory.util.ConnectionPool;

public class UserDaoImpl implements UserDao {

	private ConnectionPool connection;
	private DataSource dataSource;
	private static UserDaoImpl instance;

	private UserDaoImpl() {
		try {
			connection = ConnectionPool.getInstance();
			dataSource = connection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserDaoImpl getInstance() {
		if(instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
	}

	public int create(User user) {
		String sql = "insert into user (username,password,fullname,email) values (?,?,?,?)";
		int result = 0;
		
		try (
				Connection conn = dataSource.getConnection(); 
				PreparedStatement p = conn.prepareStatement(sql);
		) {
			p.setString(1, user.getUsername());
			p.setString(2, user.getPassword());
			p.setString(3, user.getFullname());
			p.setString(4, user.getEmail());

			result = p.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL Error " + e.getMessage());
		}
		
		return result;
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
				String password = rs.getString("password");
				String fullname = rs.getString("fullname");
				String email = rs.getString("email");
				
				User list = new User(id, username, password, fullname, email);
				
				users.add(list);
			}
		} catch (SQLException e) {
			System.out.println("SQL error " + e.getMessage());
		}
		
		return users;
	}
	
	public User getById(int userId) {
		String sql = "select * from user where id = ?";
        ResultSet rs = null;
        User user = null;
        
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				) {
			st.setInt(1, userId);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String uname = rs.getString("username");
				String pwd = rs.getString("password");
				String fname = rs.getString("fullname");
				String email = rs.getString("email");

				user = new User(id, uname, pwd, fname, email);
				
				if(rs.getInt("active") == 1) {
					user.setActive(true);
				}
				else {
					user.setActive(false);
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Error " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	public User getByUsername(String username) {
		String sql = "select * from user where username = ?";
        ResultSet rs = null;
        User user = null;
        
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				) {
			st.setString(1, username);
			rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String uname = rs.getString("username");
				String pwd = rs.getString("password");
				String fname = rs.getString("fullname");
				String email = rs.getString("email");

				user = new User(id, uname, pwd, fname, email);
				
				if(rs.getInt("active") == 1) {
					user.setActive(true);
				}
				else {
					user.setActive(false);
				}
					
			}
		} catch (SQLException e) {
			System.out.println("SQL Error " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	public int update(int id, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
