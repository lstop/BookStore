package com.lstop.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.exception.CartException;
import com.lstop.service.CartService;
import com.lstop.service.impl.CartServiceImpl;

/**
 * Servlet implementation class CartSnumServlet
 */
public class CartSnumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartSnumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str = request.getParameter("str");
		String[] strings = str.split("[&]");
		int snum = new Integer(strings[1]); //数量
		String sid = strings[2];   //产品id
		int uid = new Integer(strings[0]); //用户id
		String type = strings[3];   //  +/-
		int pnum = new Integer(strings[4]); //总数
		CartService service = new CartServiceImpl();
		try {
			if("-".equals(type)) {
				if(snum == 1) {
					//删除
					snum--;
					service.deletecart(sid, uid);
				}
				else snum--;
			}
			if("+".equals(type)) {
				if(snum == pnum) {
					//不变
				}
				else snum++;
			}
			if(snum!=0)
				service.uodatecart(sid, uid, snum);
			PrintWriter writer = response.getWriter();
			writer.write("success");
			writer.flush();
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
