package com.lcom_test.example.response;

import java.util.List;
import com.lcom_test.example.domain.Pagination;
import com.lcom_test.example.domain.UserInfo;

public class ListResponse {
	private List<UserInfo> userlist;
	private Pagination pagination;
	

	public ListResponse(List<UserInfo> userlist, Pagination pagination) {
		this.userlist = userlist;
		this.pagination = pagination;
	}

	public List<UserInfo> getUserlist() {
		return userlist;
	}


	public void setUserlist(List<UserInfo> userlist) {
		this.userlist = userlist;
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
}	

