package com.lcom_test.example.domain;

import java.util.List;

public class Product {

	private int pId;		// 제품 번호
	private String pName;	// 제품 이름
	private String pDate;	// 제품 등록일자
	private int pPrice;	// 제품 가격
	private String pFrom;	// 제품 원산지
	private String pBrand;	// 제품 브랜드
	private int pLike; 		// 제품 추천수
	private boolean pHeart; // 제품의 추천을 눌렸는지 구분
	private String username;
	private String iName;
	private int pageOpt;
	private List<Images> listImages;
	private List<Heart> listHeart;
	private List<UserInfo> listUserInfo;
	
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
	public int getpLike() {
		return pLike;
	}
	public void setpLike(int pLike) {
		this.pLike = pLike;
	}
	public boolean ispHeart() {
		return pHeart;
	}
	public void setpHeart(boolean pHeart) {
		this.pHeart = pHeart;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Heart> getListHeart() {
		return listHeart;
	}
	public void setListHeart(List<Heart> listHeart) {
		this.listHeart = listHeart;
	}
	public List<UserInfo> getListUserInfo() {
		return listUserInfo;
	}
	public void setListUserInfo(List<UserInfo> listUserInfo) {
		this.listUserInfo = listUserInfo;
	}
	public int getPageOpt() {
		return pageOpt;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public void setPageOpt(int pageOpt) {
		this.pageOpt = pageOpt;
	}

}