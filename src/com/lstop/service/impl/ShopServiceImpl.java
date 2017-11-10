package com.lstop.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lstop.dao.ShopDao;
import com.lstop.dao.impl.ShopDaoImpl;
import com.lstop.domain.PageBean;
import com.lstop.domain.Shop;
import com.lstop.exception.ShopException;
import com.lstop.service.ShopService;

public class ShopServiceImpl implements ShopService {
	ShopDao shopDaoImpl = new ShopDaoImpl();
	@Override
	public void addShop(Shop shop) throws SQLException, ShopException {
		// TODO Auto-generated method stub
		if(shopDaoImpl.insertShop(shop)<=0)
			throw new ShopException("添加失败");
	}

	@Override
	public List<Shop> queryShopList(int page,int count) throws SQLException {
		// TODO Auto-generated method stub
		//一页显示5个
		return shopDaoImpl.queryShopList(page,count);
	}

	@Override
	public void deleteshop(String id) throws SQLException, ShopException {
		if(shopDaoImpl.deleteshop(id)<=0)
			throw new ShopException("删除失败");
	}

	@Override
	public void updateshop(Shop shop) throws SQLException, ShopException {
		if(shopDaoImpl.updateshop(shop)<=0)
			throw new ShopException("修改失败");
		
	}

	@Override
	public int countshop() throws SQLException {
		return shopDaoImpl.countshop().size();
	}


	@Override
	public List<Shop> searchShopList(String id, String name, String category, String minprice, String maxprice,
			int page) throws SQLException, ShopException {
		// TODO Auto-generated method stub
		return shopDaoImpl.searchShopList(id, name, category, minprice, maxprice, page,5);
	}

	@Override
	public int searchShopCount(String id, String name, String category, String minprice, String maxprice)
			throws SQLException, ShopException {
		// TODO Auto-generated method stub
		return shopDaoImpl.searchShopCount(id, name, category, minprice, maxprice);
	}

	@Override
	public PageBean queryDataByPage(int pageNo, int pageSize, String category) throws SQLException {
		PageBean pageBean = new PageBean();
		pageBean.setCategory(category);
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(pageSize);
		List<Shop> list = shopDaoImpl.queryShopByPage(pageNo, pageSize, category);
		pageBean.setList(list);

		// 调用dao层查询指定分类的总的数据量
		long totalCount = shopDaoImpl.queryTotalCountByCategory(category);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		pageBean.setTotalPage(totalPage);
		int startPos = 0;
		int endPos = 0;
		if (totalPage > 0) {
			if (totalPage < 6) {
				startPos = 1;
				endPos = totalPage;
			} else {
				if (pageNo < 4) {
					// 判断前面的几个特殊的
					startPos = 1;
					endPos = 5;
				} else if ((totalPage - pageNo) < 5) {
					// 判断后面的几个特殊的
					startPos = totalPage - 4;
					endPos = totalPage;
				} else {
					// 中间的
					startPos = pageNo - 2;
					endPos = pageNo + 2;
				}
			}
		}
		pageBean.setEndPos(endPos);
		pageBean.setStartPos(startPos);

		return pageBean;
	}

	@Override
	public Shop queryShopById(String id) throws SQLException, ShopException {
		return shopDaoImpl.queryShopById(id);
	}

	@Override
	public List<Shop> hotcell(int first, int second) throws SQLException, ShopException {
		List<Shop> list1 = shopDaoImpl.countshop();
		List<Shop> list2 = new ArrayList<>();
		list2.add(list1.get(first));
		list2.add(list1.get(second));
		return list2;
	}
	
}
