package com.lcom_test.example.domain;

import java.util.List;

public class Images {
	private int iId;
	private String iName;
	private String iPk;
	private int pId;
	private String pName;
	
	public String getiPk() {
		return iPk;
	}
	public void setiPk(String iPk) {
		this.iPk = iPk;
	}
	public int getiId() {
		return iId;
	}
	public void setiId(int iId) {
		this.iId = iId;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
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
}