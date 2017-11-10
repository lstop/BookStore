package com.lstop.service;

import java.sql.SQLException;
import java.util.List;

import com.lstop.domain.Shop;
import com.lstop.domain.User;
import com.lstop.exception.UserException;

public interface UserService {
	public User login(User user) throws SQLException,UserException;
	public void searchusername(String username) throws SQLException,UserException;
	public void register(User user) throws SQLException,UserException;
	public int querystatus(String username) throws SQLException,UserException;
	public void queryactivecode(String username) throws SQLException,UserException;
	public void updateuser(String id, String password, String gender, String telephone) throws SQLException,UserException;
}
