package com.lstop.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lstop.dao.ShopDao;
import com.lstop.domain.Shop;
import com.lstop.utils.C3P0Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ShopDaoImpl implements ShopDao{

	@Override
	public int insertShop(Shop shop) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into shop values(?,?,?,?,?,?,?)";
		return runner.update(sql,shop.getId(),shop.getName(),shop.getPrice(),shop.getCategory(),shop.getPnum(),shop.getImgurl(),shop.getDescription());
	}

	@Override
	public List<Shop> queryShopList(int page,int count) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		return queryRunner.query("select * from shop limit "+count*(page-1)+","+count, new BeanListHandler<Shop>(Shop.class));
		//return queryRunner.query("select * from shop",new BeanListHandler<Shop>(Shop.class));
	}
	

	@Override
	public int deleteshop(String id) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.update("delete from shop where id=?", id);
	}

	@Override
	public int updateshop(Shop shop) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update shop set name=?, price=?, category=?, pnum=?, imgurl=?, description=? where id=?";
		return runner.update(sql,shop.getName(),shop.getPrice(),shop.getCategory(),shop.getPnum(),shop.getImgurl(),shop.getDescription(),shop.getId());
	}

	@Override
	public List<Shop> countshop() throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		return runner.query("select * from shop",new BeanListHandler<Shop>(Shop.class));
		}

	@Override
	public List<Shop> searchShopList(String id, String name, String category, String minprice, String maxprice,
			int page,int count) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from shop where";
		int have = 0;
		if(id!=null && !"".equals(id)) {
			sql += " id='"+id+"'";
			have = 1;
		}
		else have = 0;
		if(name!=null && !"".equals(name)) {
			if(have == 1)
				sql += " and";
			sql += " name like '%"+name+"%'";
			have = 1;
		}
		else have = 0;
		if(category!=null && !"".equals(category)) {
			if(have == 1)
				sql += " and";
			sql += " category="+category;
			have = 1;
		}
		else have = 0;
		if(minprice!=null && !"".equals(minprice)) {
			if(have == 1)
				sql += " and";
			int min = new Integer(minprice);
			if(maxprice!=null && !"".equals(maxprice)) {
				int max = new Integer(maxprice);
				sql += " price>="+min+" and price<="+max;
			}
			else {
				sql += " price>="+min;
			}
		}
		else {
			if(have == 1)
				sql += " and";
			if(maxprice!=null && !"".equals(maxprice)) {
				int max = new Integer(maxprice);
				sql += " price<="+max;
			}
		}
		if(sql.endsWith("where")) {
			sql = new StringBuffer(sql).substring(0,sql.length()-5).toString();
		}
		sql += " limit "+count*(page-1)+","+count;
		
		return runner.query(sql, new BeanListHandler<Shop>(Shop.class));
	}

	@Override
	public int searchShopCount(String id, String name, String category, String minprice, String maxprice)
			throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from shop where";
		int have = 0;
		if(id!=null && !"".equals(id)) {
			sql += " id="+id;
			have = 1;
		}
		else have = 0;
		if(name!=null && !"".equals(name)) {
			if(have == 1)
				sql += " and";
			sql += " name like '%"+name+"%'";
			have = 1;
		}
		else have = 0;
		if(category!=null && !"".equals(category)) {
			if(have == 1)
				sql += " and";
			sql += " category="+category;
			have = 1;
		}
		else have = 0;
		if(minprice!=null && !"".equals(minprice)) {
			if(have == 1)
				sql += " and";
			int min = new Integer(minprice);
			if(maxprice!=null && !"".equals(maxprice)) {
				int max = new Integer(maxprice);
				sql += " price>="+min+" and price<="+max;
			}
			else {
				sql += " price>="+min;
			}
		}
		else {
			if(have == 1)
				sql += " and";
			if(maxprice!=null && !"".equals(maxprice)) {
				int max = new Integer(maxprice);
				sql += " price<="+max;
			}
		}
		List<Shop> list = runner.query(sql, new BeanListHandler<Shop>(Shop.class));
		return list.size();
	}

	@Override
	public List<Shop> queryShopByPage(int pageNo, int pageSize, String category) throws SQLException{
		QueryRunner queryRunner =new QueryRunner(C3P0Utils.getDataSource());
		String sql="";
		if ("".equals(category)||null==category) {
			sql+="select * from shop  limit ?,?";
		}else {
			sql+="select * from shop where category="+"'"+category+"'"+" limit ?,?";
		}
		return queryRunner.query(sql, new BeanListHandler<Shop>(Shop.class),(pageNo-1)*pageSize,pageSize);
	
	}

	@Override
	public long queryTotalCountByCategory(String category) throws SQLException {
		QueryRunner queryRunner =new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(*) from shop";
		if ("".equals(category)||null==category) {
			
		}else {
			sql+="  where category= "+"'"+category+"'";
		}
		Object object = queryRunner.query(sql, new ScalarHandler());
		long totalCount =(long)object;
		return totalCount;
	}

	@Override
	public Shop queryShopById(String id) throws SQLException {
		QueryRunner queryRunner =new QueryRunner(C3P0Utils.getDataSource());
		return	queryRunner.query("select * from shop where id =?", 
				new BeanHandler<Shop>(Shop.class),id);
	}

}
