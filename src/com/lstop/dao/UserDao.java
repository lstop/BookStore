package com.lstop.dao;

import java.sql.SQLException;
import java.util.List;

import com.lstop.domain.Shop;
import com.lstop.domain.User;
import com.lstop.exception.UserException;

public interface UserDao {
	public  User userlogin(User user) throws SQLException;
	public List<User> searchusername(String username) throws SQLException;
	public  int userregister(User user) throws SQLException;
	public List<User> querystatus(String username) throws SQLException;
	public int queryactivecode(String username) throws SQLException;
	public int updateuser(String id,String password,String gender,String telephone) throws SQLException;
}
