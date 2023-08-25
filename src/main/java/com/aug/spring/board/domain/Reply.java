package com.aug.spring.board.domain;

import java.sql.Date;

public class Reply {
 private int replyNo;
 private int refBoardNo;
 private String replyContent;
 private String replyWriter;
 private Date rCreateDate;
 private Date rupdateDate;
 private char updateYN;
 private char rStatus;
public int getReplyNo() {
	return replyNo;
}
public void setReplyNo(int replyNo) {
	this.replyNo = replyNo;
}
public int getRefBoardNo() {
	return refBoardNo;
}
public void setRefBoardNo(int refBoardNo) {
	this.refBoardNo = refBoardNo;
}
public String getReplyContent() {
	return replyContent;
}
public void setReplyContent(String replyContent) {
	this.replyContent = replyContent;
}
public String getReplyWriter() {
	return replyWriter;
}
public void setReplyWriter(String replyWriter) {
	this.replyWriter = replyWriter;
}
public Date getrCreateDate() {
	return rCreateDate;
}
public void setrCreateDate(Date rCreateDate) {
	this.rCreateDate = rCreateDate;
}
public Date getRupdateDate() {
	return rupdateDate;
}
public void setRupdateDate(Date rupdateDate) {
	this.rupdateDate = rupdateDate;
}
public char getUpdateYN() {
	return updateYN;
}
public void setUpdateYN(char updateYN) {
	this.updateYN = updateYN;
}
public char getrStatus() {
	return rStatus;
}
public void setrStatus(char rStatus) {
	this.rStatus = rStatus;
}
@Override
public String toString() {
	return "Reply [댓글번호=" + replyNo + ", 게시판 번호=" + refBoardNo + ", 댓글내용=" + replyContent
			+ ", 댓글 작성자=" + replyWriter + ", 댓글 작성일자=" + rCreateDate + ", 수정여부=" + updateYN + ", 댓글상태="
			+ rStatus + "]";
}
 
 
}
