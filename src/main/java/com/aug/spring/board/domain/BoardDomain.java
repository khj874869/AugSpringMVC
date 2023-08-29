package com.aug.spring.board.domain;

import java.sql.Date;

public class BoardDomain {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFilename;
	private String boardFilerename;
	private String boardFilepath;
	private long boardFilelength;
	private int boardCount;
	private Date bCreateDate;
	private Date bUpdateDate;
	private char bStatus;
	public long getBoardFilelength() {
		return boardFilelength;
	}
	public void setBoardFilelength(long boardFilelength) {
		this.boardFilelength = boardFilelength;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public void setBoardFilename(String boardFilename) {
		this.boardFilename = boardFilename;
	}
	public String getBoardFilerename() {
		return boardFilerename;
	}
	public void setBoardFilerename(String boardFilerename) {
		this.boardFilerename = boardFilerename;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public void setBoardFilepath(String boardFilepath) {
		this.boardFilepath = boardFilepath;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public Date getbCreateDate() {
		return bCreateDate;
	}
	public void setbCreateDate(Date bCreateDate) {
		this.bCreateDate = bCreateDate;
	}
	public Date getbUpdateDate() {
		return bUpdateDate;
	}
	public void setbUpdateDate(Date bUpdateDate) {
		this.bUpdateDate = bUpdateDate;
	}
	public char getbStatus() {
		return bStatus;
	}
	public void setbStatus(char bStatus) {
		this.bStatus = bStatus;
	}
	@Override
	public String toString() {
		return "BoardDomain [게시판 번호=" + boardNo + ", 게시판 제목=" + boardTitle + ", boardContent=" + boardContent
				+ ", 작성자=" + boardWriter + ", 파일이름=" + boardFilename + ", 파일재수정="
				+ boardFilerename + ", 파일경로=" + boardFilepath + ", 파일길이=" + boardFilelength+ ", 조회수=" + boardCount + ", 만든일자="
				+ bCreateDate + ", 수정일자=" + bUpdateDate + ", 상태=" + bStatus + "]";
	}
}
