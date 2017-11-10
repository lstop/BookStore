package com.lstop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.Shop;
import com.lstop.exception.ShopException;
import com.lstop.service.ShopService;
import com.lstop.service.impl.ShopServiceImpl;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		ShopService service =new ShopServiceImpl();
		String id = request.getParameter("search_id");
		String name = request.getParameter("search_name");
		String category = request.getParameter("search_category");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		try {
			List<Shop> list = new ArrayList<Shop>();
			int size = 0;
			if((id==null||id=="") && (name==null||name=="") && (category==null||category=="") && (minprice==null||minprice=="") && (maxprice==null||maxprice=="")) {
				list = service.queryShopList(1,5);
				size = service.countshop();
			}
			else {
				list = service.searchShopList(id, name, category, minprice, maxprice, 1);
				size = service.searchShopCount(id, name, category, minprice, maxprice);
			}
			request.setAttribute("list", list);
			request.setAttribute("size", (size+5)/5);
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("category", category);
			request.setAttribute("minprice", minprice);
			request.setAttribute("maxprice", maxprice);
			request.getRequestDispatcher("admin/products/list.jsp").forward(request, response);
		} catch (SQLException | ShopException e) {
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
