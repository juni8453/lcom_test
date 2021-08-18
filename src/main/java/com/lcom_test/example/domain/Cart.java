package com.lcom_test.example.domain;

import java.util.List;

public class Cart {
	
	private int ctId;
	private int pId;
	private String username;
	private boolean check;
	private int pageOpt;
	private List<Product> listCart;
	
	public int getCtId() {
		return ctId;
	}
	public int getpId() {
		return pId;
	}
	public String getUsername() {
		return username;
	}
	public void setCtId(int ctId) {
		this.ctId = ctId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Product> getListCart() {
		return listCart;
	}
	public void setListCart(List<Product> listCart) {
		this.listCart = listCart;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public int getPageOpt() {
		return pageOpt;
	}
	public void setPageOpt(int pageOpt) {
		this.pageOpt = pageOpt;
	}
}