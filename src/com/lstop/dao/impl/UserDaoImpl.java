package com.lstop.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lstop.dao.UserDao;
import com.lstop.domain.Shop;
import com.lstop.domain.User;
import com.lstop.utils.C3P0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserDaoImpl implements UserDao {

	@Override
	public User userlogin(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.query("select * from user where username =? and password =?",
				new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}

	@Override
	public List<User> searchusername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.query("select * from user where username=?", new BeanListHandler<User>(User.class), username);
	}

	@Override
	public int userregister(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		int result =  runner.update("insert into user(email,username,password,gender,telephone,introduce,activecode) values(?,?,?,?,?,?,?)",
				user.getEmail(),user.getUsername(),user.getPassword(),user.getGender(),user.getTelephone(),user.getIntroduce(),user.getActivecode());
		return result;
	}

	@Override
	public List<User> querystatus(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.query("select * from user where username=?", new BeanListHandler<User>(User.class), username);
	}

	@Override
	public int queryactivecode(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.update("update user set state=1 where username=?", username);
	}


	@Override
	public int updateuser(String id, String password, String gender, String telephone) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update user set gender='"+gender+"'";
		if(password!=null && password!="")
			sql += ",password='"+password+"'";
		if(telephone!=null && telephone!="")
			sql += ",telephone='"+telephone+"'";
		sql += " where id="+id;
		return runner.update(sql);
	}

}
