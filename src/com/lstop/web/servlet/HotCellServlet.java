package com.lstop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.Shop;
import com.lstop.exception.ShopException;
import com.lstop.service.ShopService;
import com.lstop.service.impl.ShopServiceImpl;

/**
 * Servlet implementation class HotCellServlet
 */
public class HotCellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotCellServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShopService service = new ShopServiceImpl();
		try {
			int size = service.countshop();
			int first = (int)(Math.random()*(size-1)+1);
			int second = (int)(Math.random()*(size-1)+1);
			while(second == first) {
				second = (int)(Math.random()*(size-1)+1);
			}
			List<Shop> list= service.hotcell(first, second);
			request.getSession().setAttribute("hotcell_list", list);
			response.sendRedirect("index.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ShopException e) {
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
