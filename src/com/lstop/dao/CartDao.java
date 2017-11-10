package com.lstop.dao;

import java.sql.SQLException;
import java.util.List;

import com.lstop.domain.Cart;

public interface CartDao {
	public List<Cart> queryshopno(int uid) throws SQLException;

	public int insertcart(int uid, String sid, int shopno, String name, double price, int snum, int pnum) throws SQLException;
	public List<Cart> queryall(int uid) throws SQLException;

	public int updatecart(int uid, String sid, int snum) throws SQLException;

	public int deletecart(int uid, String sid) throws SQLException;
	
}
