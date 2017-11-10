package com.lstop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lstop.domain.User;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UserFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		User user = (User) request2.getSession().getAttribute("login_user");
		// 获取用户角色
		String role = user.getRole();
		if (role.equals("普通用户")) {
			response2.getWriter().write("您不是管理员用户,权限不够,3秒后跳转到首页！");
			response2.setHeader("refresh", "3;url=" + request2.getContextPath() + "/index.jsp");
		} else {
			chain.doFilter(request2, response2);
		}
	}



	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
