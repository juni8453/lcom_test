package com.lcom_test.example.domain;

import java.util.List;

public class Order {
	
	private int oNum; 				// 주문 번호
	private String pName;			// 제품 이름
	private String pPrice;			// 제품 가격
	private int pQuantity;			// 제품 재고
	private String username;		// 주문자 명
	private String rName;			// 받는사람 명
	private String oName;			// 입금자 명
	private String oAddress;		// 배송지 주소
	private String oDetailAddress;	// 상세 주소
	private String oEmail;			// 주문자 이메일 주소
	private String oDemand;			// 기타 요구사항
	private String oDate;			// 주문 날짜
	private List<Images> listImages; 
	
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpPrice() {
		return pPrice;
	}
	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}
	public int getpQuantity() {
		return pQuantity;
	}
	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}
	public String getoDetailAddress() {
		return oDetailAddress;
	}
	public void setoDetailAddress(String oDetailAddress) {
		this.oDetailAddress = oDetailAddress;
	}
	public String getoEmail() {
		return oEmail;
	}
	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}
	public String getoDemand() {
		return oDemand;
	}
	public void setoDemand(String oDemand) {
		this.oDemand = oDemand;
	}
	public String getoDate() {
		return oDate;
	}
	public void setoDate(String oDate) {
		this.oDate = oDate;
	}
	public List<Images> getListImages() {
		return listImages;
	}
	public void setListImages(List<Images> listImages) {
		this.listImages = listImages;
	}
	
	
	
}
