package com.lstop.service;

import java.sql.SQLException;
import java.util.List;

import com.lstop.domain.PageBean;
import com.lstop.domain.Shop;
import com.lstop.exception.ShopException;

public interface ShopService {
	public void  addShop(Shop shop) throws SQLException,ShopException;
	public List<Shop> queryShopList(int page,int count) throws SQLException;
	public void deleteshop(String id) throws SQLException,ShopException;
	public void updateshop(Shop shop) throws SQLException,ShopException;
	public int countshop() throws SQLException;
	public List<Shop> searchShopList(String id,String name,String category,String minprice,String maxprice,int page) throws SQLException,ShopException;
	public int searchShopCount(String id,String name,String category,String minprice,String maxprice) throws SQLException,ShopException;
	public PageBean queryDataByPage(int pageNo, int pageSize, String category) throws SQLException;
	public Shop queryShopById(String id) throws SQLException,ShopException;
	public List<Shop> hotcell(int first,int second) throws SQLException,ShopException;
	
}
