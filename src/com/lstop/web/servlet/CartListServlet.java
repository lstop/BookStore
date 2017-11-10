package com.lstop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.Cart;
import com.lstop.exception.CartException;
import com.lstop.service.CartService;
import com.lstop.service.impl.CartServiceImpl;

/**
 * Servlet implementation class CartListServlet
 */
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartService service = new CartServiceImpl();
		String string = request.getParameter("uid");
		System.out.println("string="+string);
		String[] strings = string.split("[&]");
		int uid = new Integer(strings[0]);
		System.out.println(uid);
		try {
			List<Cart> list = service.queryallcart(uid);
			double total = 0.0;
			for (Cart cart : list) {
				total += cart.getSum();
			}
			request.getSession().setAttribute("cart_list", list);
			request.getSession().setAttribute("total", total);
			response.sendRedirect("cart.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
