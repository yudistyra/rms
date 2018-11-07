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
import javax.sql.DataSource;

import com.yudis.inventory.dao.UserDaoImpl;
import com.yudis.inventory.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "UserController", urlPatterns = { "/UserController" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userModel;

	@Resource(name = "jdbc/inventory")
	private DataSource dataSource;

	/**
	 * @throws ServletException
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() throws ServletException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  userModel = new UserDaoImpl(dataSource); List<User> users =
		  userModel.getAll();
		  
		  request.setAttribute("USER_LIST", users);
		  
		  RequestDispatcher disp = request.getRequestDispatcher("/list-user.jsp");
		  disp.forward(request, response);
	}

}
