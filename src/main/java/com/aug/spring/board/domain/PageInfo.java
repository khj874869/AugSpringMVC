package com.aug.spring.board.domain;

public class PageInfo {
	private int currentPage;
	private int totalCount;
	private int naviTotelCount;
	private int recordCountPerPage;
	private int naviCountPerPage;
	private int startNavi;
	private int endNavi;
	

	public PageInfo(Integer currentPage,Integer totalCount, int naviTotelCount, int recordCountPerPage, int naviCountPerPage,
			int startNavi, int endNavi) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.naviTotelCount = naviTotelCount;
		this.recordCountPerPage = recordCountPerPage;
		this.naviCountPerPage = naviCountPerPage;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNaviTotelCount() {
		return naviTotelCount;
	}

	public void setNaviTotelCount(int naviTotelCount) {
		this.naviTotelCount = naviTotelCount;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getNaviCountPerPage() {
		return naviCountPerPage;
	}

	public void setNaviCountPerPage(int naviCountPerPage) {
		this.naviCountPerPage = naviCountPerPage;
	}

	public int getStartNavi() {
		return startNavi;
	}

	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}

	public int getEndNavi() {
		return endNavi;
	}

	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}

	@Override
	public String toString() {
		return "PageInfo [현재 페이지=" + currentPage + ", 총 게시물 갯수=" + totalCount + ", 네비게이션 수="
				+ naviTotelCount + ", 페이지 당 보여줄 네비개수=" + recordCountPerPage + ", 현재페이지 당 네비 수="
				+ naviCountPerPage + ", 시작네비=" + startNavi + ", 종료네비=" + endNavi + "]";
	}
	
	
}
