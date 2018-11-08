package com.yudis.inventory.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.yudis.inventory.dao.ProductDaoImpl;
import com.yudis.inventory.model.Product;

/**
 * Servlet implementation class UpdateProductController
 */
@WebServlet("/UpdateProductController")
public class UpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDaoImpl productModel;
	
	@Resource(name = "jdbc/inventory")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		productModel = new ProductDaoImpl(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
        
        Product product = productModel.get(productId);
        
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
		
		productModel.update(newproduct);
		
		response.sendRedirect("ProductController");
	}

}
