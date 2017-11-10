package com.lstop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.Shop;
import com.lstop.exception.ShopException;
import com.lstop.service.ShopService;
import com.lstop.service.impl.ShopServiceImpl;

/**
 * Servlet implementation class FindShopByIdServlet
 */
public class FindShopByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindShopByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		
		//2.调用service层查询该商品
		ShopService service =new ShopServiceImpl();
		try {
			Shop shop = service.queryShopById(id);
			request.setAttribute("shop", shop);
			request.getRequestDispatcher("product_info.jsp").forward(request, response);
		} catch (SQLException | ShopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("query_error_msg", e.getMessage());
			request.getRequestDispatcher("product_info.jsp").forward(request, response);
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
