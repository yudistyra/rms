package com.yudis.inventory.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yudis.inventory.dao.ProductDaoImpl;
import com.yudis.inventory.dao.UserDaoImpl;
import com.yudis.inventory.service.ProductServices;
import com.yudis.inventory.service.UserServices;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserServices userServices;
	private ProductServices productServices;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userServices = UserServices.getInstance();
		productServices = ProductServices.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int is_login = (int) request.getSession().getAttribute("is_login");
			
			if(is_login != 1)
				response.sendRedirect("index.jsp");
			else {
				int countProduct = productServices.countAll();
				int countUser = userServices.getCountUser();
				
				request.setAttribute("PRODUCT", countProduct);
				request.setAttribute("USERS", countUser);
				RequestDispatcher disp = request.getRequestDispatcher("/dashboard.jsp");
				disp.forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	
}
