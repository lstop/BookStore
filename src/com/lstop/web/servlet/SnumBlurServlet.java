package com.lstop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.exception.CartException;
import com.lstop.service.CartService;
import com.lstop.service.impl.CartServiceImpl;

/**
 * Servlet implementation class SnumBlurServlet
 */
public class SnumBlurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SnumBlurServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String string = request.getParameter("str");
		System.out.println(string);
		String[] strings = string.split("[&]");
		int uid = new Integer(strings[0]);
		String sid = strings[1];
		int pnum = new Integer(strings[2]);
		int snum = new Integer(request.getParameter("snum"));
		System.out.println(snum);
		CartService service = new CartServiceImpl();
		try {
			if (snum <= 0)
				service.deletecart(sid, uid);
			else {
				if (snum >= pnum)
					snum = pnum;
				service.uodatecart(sid, uid, snum);
			}
			request.setAttribute("uid", uid);
			request.getRequestDispatcher("CartListServlet").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sqlex"+e.getMessage());
		} catch (CartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("cartex"+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
