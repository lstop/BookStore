package com.lstop.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lstop.dao.CartDao;
import com.lstop.dao.ShopDao;
import com.lstop.dao.impl.CartDaoImpl;
import com.lstop.dao.impl.ShopDaoImpl;
import com.lstop.domain.Cart;
import com.lstop.domain.Shop;
import com.lstop.exception.CartException;
import com.lstop.exception.ShopException;

public class CartServiceImpl implements com.lstop.service.CartService {
	ShopDao shopDaoImpl = new ShopDaoImpl();
	CartDao cartDaoImpl = new CartDaoImpl();
	@Override
	public void addtocart(String sid, int uid) throws SQLException, ShopException, CartException {
		// TODO Auto-generated method stub
		Shop shop = shopDaoImpl.queryShopById(sid);
		List<Cart> list = cartDaoImpl.queryall(uid);
		int index = -1;
		for (int i=0;i<list.size();i++) {
			if(list.get(i).getSid().equals(sid))
				index = i;
		}
		if(index!=-1) {
			int snum = list.get(index).getSnum();
			int pnum = list.get(index).getPnum();
			if(snum<pnum) snum++;
			if(cartDaoImpl.updatecart(uid,sid,snum)<0) {
				throw new CartException("加入购物车失败");
			}
		}
		else {
			int shopno = cartDaoImpl.queryshopno(uid).size()+1;
			if(cartDaoImpl.insertcart(uid,sid,shopno,shop.getName(),shop.getPrice(),1,shop.getPnum())<1) {
				throw new CartException("加入购物车失败");
			}
		}
		
	}
	@Override
	public List<Cart> queryallcart(int uid) throws SQLException, CartException {
		List<Cart> list= cartDaoImpl.queryall(uid);
		return list;
	}
	@Override
	public void uodatecart(String sid, int uid, int snum) throws SQLException, CartException {
		if(cartDaoImpl.updatecart(uid, sid, snum)<0) {
			throw new CartException("更新失败");
		}
	}
	@Override
	public void deletecart(String sid, int uid) throws SQLException, CartException {
		if(cartDaoImpl.deletecart(uid, sid)<0) {
			throw new CartException("更新失败");
		}
	}

}
