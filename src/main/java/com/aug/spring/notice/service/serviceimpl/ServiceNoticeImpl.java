package com.aug.spring.notice.service.serviceimpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aug.spring.notice.domain.Notice;
import com.aug.spring.notice.domain.PageInfo;
import com.aug.spring.notice.service.ServiceNotice;
import com.aug.spring.notice.store.NoticeStore;


@Service
public class ServiceNoticeImpl implements ServiceNotice {
	@Autowired
	private NoticeStore store;
	@Autowired
	private SqlSession session;
	@Override
	public int insertNotice(Notice notice) {
		int result = store.inserNotice(session,notice);
		return result;
	}
	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = store.findList(session, pInfo);
		return nList;
	}
	@Override
	public int getListCount() {
		int result = store.selectListCount(session);
		return result;
	}
	@Override
	public List<Notice> searchNoticeByTitle(String searchKeyword) {
		List<Notice> nList = store.searchBySubject(session,searchKeyword);
		return nList;
	}
	@Override
	public List<Notice> searchContentByCotent(String searchKeyword) {
		List<Notice> sList = store.searchContentByKeyword(session,searchKeyword);
		return sList;
	}
	@Override
	public List<Notice> searchWriterBysearch(String searchKeyword) {
		List<Notice> sList = store.searchWriterByKeyword(session,searchKeyword);
		return sList;
	}
	@Override
	public List<Notice> searchAllData(String searchKeyword) {
		List<Notice> sList = store.searchAllByKeyword(session,searchKeyword);
		return sList;
	}
	@Override
	public List<Notice> searchNoticesByKeyword(PageInfo pInfo,Map<String,String> paraMap ) {
		List<Notice> sList = store.searchByKeyword(session,paraMap,pInfo);
		return sList;
	}
	@Override
	public int getListCount(Map<String, String> paraMap) {
		int result = store.searchforMap(session,paraMap);
		return result;
	}
	@Override
	public Notice selectNoticeByNo(int noticeNo) {
		Notice notice = store.detailNotice(session,noticeNo);
		return notice;
	}
	@Override
	public int updateNotice(Notice notice) {
		int result = store.updateNotice(session,notice);
		return result;
	}
}
