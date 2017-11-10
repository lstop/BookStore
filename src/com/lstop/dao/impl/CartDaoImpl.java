package com.lstop.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lstop.dao.CartDao;
import com.lstop.domain.Cart;
import com.lstop.utils.C3P0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CartDaoImpl implements CartDao {

	@Override
	public List<Cart> queryshopno(int uid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.query("select * from cart where uid="+uid, new BeanListHandler<Cart>(Cart.class));
	}

	@Override
	public int insertcart(int uid, String sid, int shopno, String name, double price, int snum, int pnum) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into cart(uid,sid,snum,shopno,sum) values(?,?,?,?,?)";
		return runner.update(sql,uid,sid,snum,shopno,snum*price);
	}

	@Override
	public List<Cart> queryall(int uid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select cart.uid,sid,shopno,snum,sum,shop.name as 'sname',shop.price as 'sprice',shop.pnum"+
				" from cart,shop where cart.sid=shop.id and cart.uid="+uid;
		return runner.query(sql, new BeanListHandler<Cart>(Cart.class));
	}

	@Override
	public int updatecart(int uid, String sid, int snum) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update cart set snum=?,sum=?*(select price from shop where id=?) where uid="+uid+" and sid='"+sid+"'";
		return runner.update(sql,snum,snum,sid);
	}

	@Override
	public int deletecart(int uid, String sid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.update("delete from cart where uid="+uid+" and sid='"+sid+"'");
	}

}
