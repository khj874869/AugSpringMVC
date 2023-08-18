package com.aug.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.aug.spring.notice.domain.Notice;
import com.aug.spring.notice.domain.PageInfo;

public interface ServiceNotice {

	int insertNotice(Notice notice);

	List<Notice> selectNoticeList(PageInfo pInfo);

	int getListCount();

	List<Notice> searchNoticeByTitle(String searchKeyword);

	List<Notice> searchContentByCotent(String searchKeyword);

	List<Notice> searchWriterBysearch(String searchKeyword);

	List<Notice> searchAllData(String searchKeyword);
	/**
	 * 공지사항 조건에 따라 키워드로 검색 Service
	 * @param pInfo 
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String,String> paraMap );
	/**
	 * 조건에 따라 검색 바운드 만들기 (10개씩)
	 * @param paraMap
	 * @return
	 */
	int getListCount(Map<String, String> paraMap);

}
