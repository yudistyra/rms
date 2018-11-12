package com.yudis.inventory.controller;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yudis.inventory.service.ProductServices;

/**
 * Servlet implementation class CreateProductController
 */
@WebServlet("/CreateProductController")
public class CreateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductServices productService;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		productService = new ProductServices();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int is_login = (int) session.getAttribute("is_login");
		
		if(is_login != 1)
			response.sendRedirect("index.jsp");
		else {
			RequestDispatcher disp = request.getRequestDispatcher("/createproduct.jsp");
			disp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		
		String result = productService.create(name, desc, price);
		
		if(result.equalsIgnoreCase("success")) {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-success alert-dismissible");
			request.setAttribute("MESSAGE", "Product has been added");
			request.setAttribute("PRODUCT_LIST", productService.getAll());
			RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
			disp.forward(request, response);
		}
		else {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-danger alert-dismissible");
			request.setAttribute("MESSAGE", "Failed to add product");
			request.setAttribute("PRODUCT_LIST", productService.getAll());
			RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
			disp.forward(request, response);
		}
	}

	
}
