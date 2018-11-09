package com.yudis.inventory.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.yudis.inventory.dao.ProductDaoImpl;
import com.yudis.inventory.dao.UserDaoImpl;
import com.yudis.inventory.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "LoginController", urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userModel;
	private ProductDaoImpl productModel;

	@Resource(name = "jdbc/inventory")
	private DataSource dataSource;

	/**
	 * @throws ServletException
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() throws ServletException {
		super(); 
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userModel = new UserDaoImpl(dataSource);
		productModel = new ProductDaoImpl(dataSource);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int is_login = (int) request.getSession().getAttribute("is_login");
			
			if(is_login != 1)
				response.sendRedirect("index.jsp");
			else {
				int countProduct = productModel.getAll().size();
				int countUser = userModel.getAll().size();
				
				request.setAttribute("PRODUCT", countProduct);
				request.setAttribute("USERS", countUser);
				RequestDispatcher disp = request.getRequestDispatcher("/dashboard.jsp");
				disp.forward(request, response);
			}
			
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userModel.login(username, password);

		if(user != null) {
			HttpSession session = req.getSession(true);	    
			session.setAttribute("username",user.getUsername());
			session.setAttribute("fullname",user.getFullname());
			session.setAttribute("email",user.getEmail());
			session.setAttribute("is_login",1);
			
			int countProduct = productModel.getAll().size();
			int countUser = userModel.getAll().size();
			
			req.setAttribute("PRODUCT", countProduct);
			req.setAttribute("USERS", countUser);
			RequestDispatcher disp = req.getRequestDispatcher("/dashboard.jsp");
			disp.forward(req, resp);
		}
		else {
			req.setAttribute("ALERT", true);
			req.setAttribute("ALERT_CLASS", "alert alert-danger");
			req.setAttribute("MESSAGE", "User not Found. Please register first!!");
			RequestDispatcher disp = req.getRequestDispatcher("/index.jsp");
			disp.forward(req, resp);
		}
		
	}
	
	

}
