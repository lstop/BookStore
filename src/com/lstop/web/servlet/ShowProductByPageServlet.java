package com.lstop.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.PageBean;
import com.lstop.service.ShopService;
import com.lstop.service.impl.ShopServiceImpl;

/**
 * Servlet implementation class ShowProductByPageServlet
 */
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String category = request.getParameter("category");
		int pageNo=1,pageSize= 4;
		 String pageNo1  = request.getParameter("pageNo");
		 String pageSize1  = request.getParameter("pageSize");
		if (!"".equals(pageNo1) && null!=pageNo1) {
			pageNo= Integer.parseInt(pageNo1);
		}
		if (!"".equals(pageSize1) && null!=pageSize1) {
			pageSize= Integer.parseInt(pageSize1);
		}
		ShopService service =new ShopServiceImpl();
		try {
			PageBean pageBean = service.queryDataByPage(pageNo, pageSize, category);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("product_list.jsp").forward(request, response);
		} catch (SQLException e) {
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
