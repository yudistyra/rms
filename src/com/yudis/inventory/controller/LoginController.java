package com.yudis.inventory.controller;

import java.io.IOException;  

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yudis.inventory.model.User;
import com.yudis.inventory.service.UserServices;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "LoginController", urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServices userService;

	/**
	 * @throws ServletException
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() throws ServletException {
		super(); 
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		userService = UserServices.getInstance();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int is_login = (int) request.getSession().getAttribute("is_login");
		
		if(is_login != 1)
			response.sendRedirect("index.jsp");
		else {
			RequestDispatcher disp = request.getRequestDispatcher("DashboardController");
			disp.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userService.login(username, password);

		if(user != null) {
			if(user.isActive()) {
				HttpSession session = req.getSession(true);	    
				session.setAttribute("username",user.getUsername());
				session.setAttribute("fullname",user.getFullname());
				session.setAttribute("email",user.getEmail());
				session.setAttribute("is_login",1);
				
				RequestDispatcher disp = req.getRequestDispatcher("DashboardController");
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
		else {
			req.setAttribute("ALERT", true);
			req.setAttribute("ALERT_CLASS", "alert alert-danger");
			req.setAttribute("MESSAGE", "User not Found. Please register first!!");
			RequestDispatcher disp = req.getRequestDispatcher("/index.jsp");
			disp.forward(req, resp);
		}
		
	}
	
	

}
