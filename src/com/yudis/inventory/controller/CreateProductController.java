package com.yudis.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
import com.yudis.inventory.model.Product;

/**
 * Servlet implementation class CreateProductController
 */
@WebServlet("/CreateProductController")
public class CreateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDaoImpl productModel;
    
    @Resource(name = "jdbc/inventory")
    private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProductController() {
        super();
        // TODO Auto-generated constructor stub
    }
    

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
		try {
			HttpSession session = request.getSession(false);
			int is_login = (int) session.getAttribute("is_login");
			String username = (String) session.getAttribute("username");
			
			if(is_login != 1)
				response.sendRedirect("index.jsp");
			else {
				RequestDispatcher disp = request.getRequestDispatcher("/createproduct.jsp");
				disp.forward(request, response);
			}
			
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product newproduct = new Product();
		
		newproduct.setName(request.getParameter("name"));
		newproduct.setDescription(request.getParameter("description"));
		newproduct.setPrice(Integer.parseInt(request.getParameter("price")));
		
		productModel.create(newproduct);
		
		response.sendRedirect("ProductController");
	}

	
}
