package com.lstop.domain;

import java.io.Serializable;

public class Cart implements Serializable{
	private int uid;
	private String sid;
	private int shopno;
	private String sname;
	private double sprice;
	private int pnum;
	private int snum;
	private double sum;
	public Cart(int uid, String sid, int shopno, String sname, double sprice, int pnum, int snum, double sum) {
		super();
		this.uid = uid;
		this.sid = sid;
		this.shopno = shopno;
		this.sname = sname;
		this.sprice = sprice;
		this.pnum = pnum;
		this.snum = snum;
		this.sum = sum;
	}
	public Cart() {
		super();
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getShopno() {
		return shopno;
	}
	public void setShopno(int shopno) {
		this.shopno = shopno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public double getSprice() {
		return sprice;
	}
	public void setSprice(double sprice) {
		this.sprice = sprice;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "Cart [uid=" + uid + ", sid=" + sid + ", shopno=" + shopno + ", sname=" + sname + ", sprice=" + sprice
				+ ", pnum=" + pnum + ", snum=" + snum + ", sum=" + sum + "]";
	}
	
}
