package com.lstop.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


import com.lstop.domain.Shop;
import com.lstop.domain.User;
import com.lstop.exception.UserException;
import com.lstop.service.UserService;
import com.lstop.service.impl.UserServiceImpl;
import com.lstop.utils.MD5Utils;

/**
 * Servlet implementation class UserLoginServlet
 */
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
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
		String remember = request.getParameter("remember");
		String auto = request.getParameter("auto");
		String pwd = request.getParameter("password");
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPassword(MD5Utils.pwdByMD5(user.getPassword())); 
		//202cb962ac59075b964b07152d234b70
		//202cb962ac59075b964b07152d234b70
		UserService us = new UserServiceImpl();
		try {
			User user2 = us.login(user);
			// 登录成功
			
			// 后续要自动登录
			// 1.保存用户名密码到cookie
			String re=user.getUsername() + "&" + user.getPassword() + "&checked='checked'";
			Cookie cookie = new Cookie("userinfo", re);
			String status = user.getUsername()+"&"+pwd+"&checked='checked'";
			Cookie cookie2 = new Cookie("status", status);
			if (auto != null) {
				//自动登录：用户信息要存储7天
				cookie.setMaxAge(7 * 24 * 60 * 60);
			} else {
				//不自动登录
				cookie.setMaxAge(0);
			}
			if(remember!=null) {
				cookie2.setMaxAge(7*24*60*60);
			}
			else {
				cookie2.setMaxAge(0);
			}
				
			//响应给浏览器
			response.addCookie(cookie);
			response.addCookie(cookie2);
			// 2.保存用户状态session
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("login_user", user2);
			request.getSession().setMaxInactiveInterval(-1);
            response.sendRedirect("index.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_msg", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_msg", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
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
