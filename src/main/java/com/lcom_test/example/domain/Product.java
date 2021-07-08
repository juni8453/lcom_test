package com.lcom_test.example.domain;

public class Product {

	private int p_id;		// 제품 번호
	private String p_name;	// 제품 이름
	private String p_date;	// 제품 등록일자
	private int p_price;	// 제품 가격
	private String p_from;	// 제품 원산지
	private String p_brand;	// 제품 브랜드
	
	public int getP_id() {
		return p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public int getP_price() {
		return p_price;
	}
	public String getP_from() {
		return p_from;
	}
	public String getP_brand() {
		return p_brand;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public void setP_from(String p_from) {
		this.p_from = p_from;
	}
	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}
}
