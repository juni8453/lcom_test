package com.lcom_test.example.domain;

import java.util.List;

public class Heart {

	private int hId;
	private int pId;
	private String username;
	private List<Product> listProduct;
	
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	public int gethId() {
		return hId;
	}
	public int getpId() {
		return pId;
	}
	public String getUsername() {
		return username;
	}
	public void sethId(int hId) {
		this.hId = hId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
