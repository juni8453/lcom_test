package com.lcom_test.example.domain;

import java.util.List;

public class Product {

	private int pId;		// 제품 번호
	private String pName;	// 제품 이름
	private String pDate;	// 제품 등록일자
	private int pPrice;	// 제품 가격
	private String pFrom;	// 제품 원산지
	private String pBrand;	// 제품 브랜드
	private List<Images> listImages;
	
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpDate() {
		return pDate;
	}
	public void setpDate(String pDate) {
		this.pDate = pDate;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getpFrom() {
		return pFrom;
	}
	public void setpFrom(String pFrom) {
		this.pFrom = pFrom;
	}
	public String getpBrand() {
		return pBrand;
	}
	public void setpBrand(String pBrand) {
		this.pBrand = pBrand;
	}
	public List<Images> getListImages() {
		return listImages;
	}
	public void setListImages(List<Images> listImages) {
		this.listImages = listImages;
	}
	
	
}