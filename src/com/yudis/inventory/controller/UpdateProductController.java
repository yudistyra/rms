package com.yudis.inventory.controller;

import java.io.IOException;  

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yudis.inventory.model.Product;
import com.yudis.inventory.service.ProductServices;

/**
 * Servlet implementation class UpdateProductController
 */
@WebServlet("/UpdateProductController")
public class UpdateProductController extends HttpServlet {
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
		int productId = Integer.parseInt(request.getParameter("productId"));
        
        Product product = productService.get(productId);
        
        request.setAttribute("PRODUCT", product);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateproduct.jsp");
        
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product newproduct = new Product();
		
		newproduct.setId(Integer.parseInt(request.getParameter("id")));
		newproduct.setName(request.getParameter("name"));
		newproduct.setDescription(request.getParameter("description"));
		newproduct.setPrice(Integer.parseInt(request.getParameter("price")));
		
		if(request.getParameter("active") != null)
			newproduct.setActive(true);
		else
			newproduct.setActive(false);
		
		String result = productService.update(newproduct);
		
		if(result.equalsIgnoreCase("success")) {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-success alert-dismissible");
			request.setAttribute("MESSAGE", "Product has been updated");
			request.setAttribute("PRODUCT_LIST", productService.getAll());
			RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
			disp.forward(request, response);
		}
		else {
			request.setAttribute("ALERT", true);
			request.setAttribute("ALERT_CLASS", "alert alert-danger alert-dismissible");
			request.setAttribute("MESSAGE", "Failed to update product");
			request.setAttribute("PRODUCT_LIST", productService.getAll());
			RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
			disp.forward(request, response);
		}
		
		
	}

}
