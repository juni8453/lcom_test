package com.lcom_test.example.domain;

import com.lcom_test.example.domain.Search;

public class Pagination {

	private int count; 			// 전체 게시물 수
	private int page; 			// 현재 페이지
	private int pageNum; 		// db limit에 들어갈 게시물의 start index
	private int startPage;
	private int endPage;	 	// 끝 페이지
	private int lastPage; 		// 페이지네이션 마지막 페이지
	private int prevPage;		// < 클릭시 페이지네이션
	private int nextPage; 		// > 클릭시 페이지네이션
	private Search search;
	public static final int pageUnit = 5; // 한 페이지에 출력될 페이지네이션 갯수 (1~5)
	public static final int perPage = 10; // 한 페이지에 출력될 게시물 수
	
	public Pagination() {
		this(1, 0, null); // 기본생성자 (BoardEdit 사용)
	}
	
	public Pagination(int page) {
		this(page, 0, null);
	}
	
	public Pagination(int page, int count) {
		this(page, count, null);
	}
	
	public Pagination(int page, int count, Search search) {
		this.page = page;
		this.count = count;
		this.pageNum = (page-1)*perPage;					//Pagination Class 내에서 미리 pageNum 계산
		this.search = search;

		startPage = ((page-1)/pageUnit)*pageUnit+1;	
		lastPage = (int)Math.ceil(count/(float)perPage);
		endPage = startPage+pageUnit-1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage = startPage - 1;
		nextPage = (startPage+pageUnit);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public static int getPageunit() {
		return pageUnit;
	}
	
	public static int getPerpage() {
		return perPage;
	}

	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
}
