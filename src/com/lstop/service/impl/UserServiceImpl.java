package com.lstop.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.lstop.dao.UserDao;
import com.lstop.dao.impl.UserDaoImpl;
import com.lstop.domain.User;
import com.lstop.exception.UserException;
import com.lstop.service.UserService;
import com.lstop.utils.SendJMail;

public class UserServiceImpl implements UserService{
	UserDao userdao = new UserDaoImpl();
	@Override
	public User login(User user) throws SQLException, UserException {
		User user2 = userdao.userlogin(user);
		if(user2==null) 
			throw new UserException("登录失败");
		if(user2.getState()!=1)
			throw new UserException("请先激活账户");
		return user2;
	}
	@Override
	public void searchusername(String username) throws SQLException, UserException {
		List<User> list = userdao.searchusername(username);
		if(list.size() != 0) {
			throw new UserException("用户名重复");
		}
	}
	@Override
	public void register(User user) throws SQLException, UserException {
		if(userdao.userregister(user)<1) {
			throw new UserException("注册失败");
		}
		else {
			//发送邮箱激活码
			  SendJMail.sendMail(user.getEmail(),
					  "注册成功！请点击该链接进行激活！"
					  + "<a href='http://localhost:8080/BookStore/ActiveCodeServlet?username="
							  +user.getUsername()+"'>点击激活</a>");
		}
	}
	@Override
	public int querystatus(String username) throws SQLException, UserException {
		List<User> list =  userdao.querystatus(username);
		return list.get(0).getState();
	}
	@Override
	public void queryactivecode(String username) throws SQLException, UserException {
		if(userdao.queryactivecode(username)<1)
			throw new UserException("激活失败");
	}
	@Override
	public void updateuser(String id, String password, String gender, String telephone) throws SQLException, UserException {
		if(userdao.updateuser(id, password, gender, telephone)<1)
			throw new UserException("修改失败");
	}

}
