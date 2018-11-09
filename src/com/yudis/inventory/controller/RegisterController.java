package com.yudis.inventory.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.yudis.inventory.dao.UserDaoImpl;
import com.yudis.inventory.model.User;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userModel;

	@Resource(name = "jdbc/inventory")
	private DataSource dataSource;
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userModel = new UserDaoImpl(dataSource);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		
		if(password.equals(confirmpassword)) {
			User newuser = new User(username, password, fullname, email);
			userModel.create(newuser);
			
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-info");
			request.setAttribute("MESSAGE", "User has been registred");
			RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
			disp.forward(request, response);
		}
		else {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-danger");
			request.setAttribute("MESSAGE", "Password and Confirm Password don't match");
			RequestDispatcher disp = request.getRequestDispatcher("/register.jsp");
			disp.forward(request, response);
		}
	}

}
