package com.lcom_test.example.domain;

import java.util.List;

public class Board {
	private int bId;
	private int bViews;
	private int bGroup;
	private int bOrder;
	private int bDepth;
	private String username;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String bDateTime;
	private Pagination pagination;
	private List<Comment> commentList;

	
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public int getbViews() {
		return bViews;
	}
	public void setbViews(int bViews) {
		this.bViews = bViews;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbOrder() {
		return bOrder;
	}
	public void setbOrder(int bOrder) {
		this.bOrder = bOrder;
	}
	public int getbDepth() {
		return bDepth;
	}
	public void setbDepth(int bDepth) {
		this.bDepth = bDepth;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbDateTime() {
		return bDateTime;
	}
	public void setbDateTime(String bDateTime) {
		this.bDateTime = bDateTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
}	
	