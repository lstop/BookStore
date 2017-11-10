package com.lstop.dao;

import java.sql.SQLException;
import java.util.List;

import com.lstop.domain.Shop;

public interface ShopDao {
	public int insertShop(Shop shop) throws SQLException;
	public List<Shop> queryShopList(int page,int count) throws SQLException;
	public int deleteshop(String id) throws SQLException;
	public int updateshop(Shop shop) throws SQLException;
	public List<Shop> countshop() throws SQLException;
	public List<Shop> searchShopList(String id, String name, String category, String minprice, String maxprice,
			int page,int count) throws SQLException;
	public int searchShopCount(String id, String name, String category, String minprice, String maxprice) throws SQLException;
	public List<Shop> queryShopByPage(int pageNo, int pageSize, String category) throws SQLException;
	public long queryTotalCountByCategory(String category) throws SQLException;
	public Shop queryShopById(String id) throws SQLException;
	
}
