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

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.lstop.domain.Shop;
import com.lstop.exception.ShopException;
import com.lstop.service.ShopService;
import com.lstop.service.impl.ShopServiceImpl;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class DividePageServlet
 */
public class DividePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DividePageServlet() {
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
		String page = request.getParameter("page");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		int pages = 0;
		if(page==null)
			pages = 1;
		else
			pages = new Integer(page);
		PrintWriter writer = response.getWriter();
		ShopService service =new ShopServiceImpl();
		try {
			List<Shop> list = new ArrayList<Shop>();
			if((id==null||id=="") && (name==null||name=="") && (category==null||category=="") && (minprice==null||minprice=="") && (maxprice==null||maxprice=="")) {
				list = service.queryShopList(pages,5);
			}
			else {
				list = service.searchShopList(id, name, category, minprice, maxprice, pages);
			}
				
			
			JSONArray array =  JSONArray.fromObject(list);
			writer.write(array.toString());
			writer.flush();
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
