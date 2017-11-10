package com.lstop.domain;

import java.util.List;

public class PageBean {
	 private List<Shop> list;  //查询的数据集合
	 private int  pageNo;   //当前页
	 private int pageSize; //每页需要的数据
	 private int totalPage;  //总页数 ，Math.ceil(totalCount/pageSize)
	 private int startPos;   //当前页面展示的页数的开始位置
	 private int endPos;     //当前页面展示的页数的结束位置
	 private long totalCount;  //该分类的总的数据
	 private String category; //分类
	 
	 
	 
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Shop> getList() {
		return list;
	}
	public void setList(List<Shop> list) {
		this.list = list;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPos() {
		return startPos;
	}
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	public int getEndPos() {
		return endPos;
	}
	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "PageBean [list=" + list + ", pageNo=" + pageNo + ", totalPage=" + totalPage + ", startPos=" + startPos
				+ ", endPos=" + endPos + ", totalCount=" + totalCount + ", category=" + category + "]";
	}
	 
	 
	 
	 
}
