package com.lcom_test.example.response;

import java.util.List;

import com.lcom_test.example.domain.Board;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.Search;
import com.lcom_test.example.domain.UserInfo;

public class ListResponse<T> {
	private List<T> list;
	private Pagination pagination;
	//private List<UserInfo> userlist;
	//private List<Board> boardlist;
	
	public ListResponse(Pagination pagination, List<T> list) {
		this.pagination = pagination;
		this.list = list;
	}
	
	public ListResponse(List<T> list) {
		this.list = list;
	}

/*	public ListResponse(List<UserInfo> userlist,Pagination pagination) {
		this.userlist = userlist;
		this.pagination = pagination;
	}
	
	public ListResponse(Pagination pagination, List<Board> boardlist) {
		this.boardlist = boardlist;
		this.pagination = pagination;
	}
	
	public List<UserInfo> getUserlist() {
		return userlist;
	}


	public void setUserlist(List<UserInfo> userlist) {
		this.userlist = userlist;
	}


	public List<Board> getBoardlist() {
		return boardlist;
	}



	public void setBoardlist(List<Board> boardlist) {
		this.boardlist = boardlist;
	}*/



	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}	

  