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
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
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
		try {
			HttpSession session = request.getSession(false);
			int is_login = (int) session.getAttribute("is_login");
			
			if(is_login != 1)
				response.sendRedirect("index.jsp");
			else {	
				request.setAttribute("PRODUCT_LIST", productService.getAll());
				RequestDispatcher disp = request.getRequestDispatcher("/listproduct.jsp");
				disp.forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
