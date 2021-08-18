package com.lcom_test.example.domain;

public class Comment {
	
	private int cId;
	private String cContent;
	private String cDateTime;
	private int bId;
	private String username;
	private int cGroup;
	private int cOrder;
	private int cDepth;
	private Pagination pagination;
	private boolean cShow;
	private boolean cShow2;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	
	public String getcDateTime() {
		return cDateTime;
	}
	public void setcDateTime(String cDateTime) {
		this.cDateTime = cDateTime;
	}
	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getcGroup() {
		return cGroup;
	}
	public void setcGroup(int cGroup) {
		this.cGroup = cGroup;
	}
	
	public int getcOrder() {
		return cOrder;
	}
	public void setcOrder(int cOrder) {
		this.cOrder = cOrder;
	}
	
	public int getcDepth() {
		return cDepth;
	}
	public void setcDepth(int cDepth) {
		this.cDepth = cDepth;
	}
	
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public boolean iscShow() {
		return cShow;
	}
	public void setcShow(boolean cShow) {
		this.cShow = cShow;
	}
	
	public boolean iscShow2() {
		return cShow2;
	}
	public void setcShow2(boolean cShow2) {
		this.cShow2 = cShow2;
	}
}

