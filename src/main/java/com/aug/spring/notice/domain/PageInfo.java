package com.aug.spring.notice.domain;
//페이징 처리 관련 VO
public class PageInfo {
	private int recordCountPerPage;
	private int currentPage;
	private int startNavi;
	private int endNavi;
	private int naviCountPerPage;
	private int naviTotalCount;
	public PageInfo() {};
	public PageInfo(int recordCountPerPage, int currentPage, int startNavi, int endNavi, int naviCountPerPage,
			int naviTotalCount) {
		super();
		this.recordCountPerPage = recordCountPerPage;
		this.currentPage = currentPage;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
		this.naviCountPerPage = naviCountPerPage;
		this.naviTotalCount = naviTotalCount;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public int getNaviCountPerPage() {
		return naviCountPerPage;
	}
	public void setNaviCountPerPage(int naviCountPerPage) {
		this.naviCountPerPage = naviCountPerPage;
	}
	public int getNaviTotalCount() {
		return naviTotalCount;
	}
	public void setNaviTotalCount(int naviTotalCount) {
		this.naviTotalCount = naviTotalCount;
	}
	
}
