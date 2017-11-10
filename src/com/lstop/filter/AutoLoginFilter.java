package com.lstop.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.User;
import com.lstop.exception.UserException;
import com.lstop.service.UserService;
import com.lstop.service.impl.UserServiceImpl;



public class AutoLoginFilter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request =(HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		//判断是否登录(获取登录状态，其实就从session中取值)
		User user  =  (User)request.getSession().getAttribute("login_user");
		
		//去除界面Login.jsp LoginServlet  手动登录
		String path1= request.getContextPath(); //结果：/Day19_AutoLoginByFilter
		//结果：/Day19_AutoLoginByFilter/login.jsp 或者/Day19_AutoLoginByFilter/LoginServlet
		String path2 = request.getRequestURI(); 
		String path3 = path2.substring(path1.length());
		if ("/login.jsp".equals(path3)||"/UserLoginServlet".equals(path3)) {
			//手动登录，完善，在登录页面需要显示用户名和密码并且调用service层登录
		}else {
			if(test1(request, user) == 1) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		//放行
		arg2.doFilter(request, response);
	}

	private int test1(HttpServletRequest request, User user) {
		int dothrow = 0;
		if (user==null) {
			//用户未登录，可能要自动登录
			//要登录得先取出用户名和密码
			Cookie[] cookies = request.getCookies();
			if (cookies!=null&&cookies.length>0) {
				   for (int i = 0; i < cookies.length; i++) {
					 Cookie cookie =  cookies[i];
					 String name= cookie.getName();
					 if ("userinfo".equals(name)) {
						//存储的是用户名和密码
						String value =  cookie.getValue();
						if ("".equals(value)||null==value) {
							
						}else {
							//获取用户名和密码
							String[] result = value.split("&");
							String username=  result[0];
							String pwd=  result[1];
							User user2 =new User();
							user2.setPassword(pwd);
							user2.setUsername(username);
							//处理登录业务逻辑
							UserService userService =new UserServiceImpl();
							try {
							   User user3 = userService.login(user2);
							   //登录成功
							   //改变状态
							  request.getSession().setAttribute("user", user2);
							  request.getSession().setAttribute("login_user", user3);
							  request.getSession().setMaxInactiveInterval(-1);
							  //放行
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (UserException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}	
			}else {
				//不需要自动登录，直接放行界面输入内容提交登录
				dothrow = 1;
			}
		}
		return dothrow;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
