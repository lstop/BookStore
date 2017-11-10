package com.lstop.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lstop.domain.Cart;
import com.lstop.exception.CartException;
import com.lstop.exception.ShopException;

public interface CartService {
	public void addtocart(String sid,int uid) throws SQLException,ShopException,CartException;
	public List<Cart> queryallcart(int uid) throws SQLException,CartException;
	public void uodatecart(String sid,int uid,int snum) throws SQLException,CartException;
	public void deletecart(String sid,int uid) throws SQLException,CartException;
}
