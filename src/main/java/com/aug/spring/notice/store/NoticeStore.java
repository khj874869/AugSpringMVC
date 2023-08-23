package com.aug.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.aug.spring.notice.domain.Notice;
import com.aug.spring.notice.domain.PageInfo;


public interface NoticeStore {

	int inserNotice(SqlSession session, Notice notice);

	List<Notice> findList(SqlSession session,PageInfo pInfo);

	int selectListCount(SqlSession session);

	List<Notice> searchBySubject(SqlSession session, String searchKeyword);

	List<Notice> searchContentByKeyword(SqlSession session, String searchKeyword);

	List<Notice> searchWriterByKeyword(SqlSession session, String searchKeyword);

	List<Notice> searchAllByKeyword(SqlSession session, String searchKeyword);

	List<Notice> searchByKeyword(SqlSession session,Map<String,String> paraMap,PageInfo pInfo );

	int searchforMap(SqlSession session, Map<String, String> paraMap);

	Notice detailNotice(SqlSession session,int noticeNo);

	int updateNotice(SqlSession session, Notice notice);
	
}
