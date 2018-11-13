package com.yudis.inventory.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yudis.inventory.service.ProductServices;

/**
 * Servlet implementation class DeleteProductController
 */
@WebServlet("/DeleteProductController")
public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductServices productService;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		productService = ProductServices.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		String result = productService.delete(productId);
		
		if(result.equalsIgnoreCase("success")) {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-success alert-dismissible");
			request.setAttribute("MESSAGE", "Product has been deleted");
			request.setAttribute("PRODUCT_LIST", productService.getAll());
			RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
			disp.forward(request, response);
		}
		else {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-danger alert-dismissible");
			request.setAttribute("MESSAGE", "Failed to delete product");
			request.setAttribute("PRODUCT_LIST", productService.getAll());
			RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
			disp.forward(request, response);
		}
	}

}
