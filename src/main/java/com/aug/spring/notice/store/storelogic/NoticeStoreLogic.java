package com.aug.spring.notice.store.storelogic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.aug.spring.notice.domain.Notice;
import com.aug.spring.notice.domain.PageInfo;
import com.aug.spring.notice.store.NoticeStore;


@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public int inserNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice",notice);
		return result;
	}

	@Override
	public List<Notice> findList(SqlSession session,PageInfo pInfo) {
		int limit =10 ;
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice>nList = session.selectList("NoticeMapper.selectList",null,rowBounds);
		return nList;
	}

	@Override
	public int selectListCount(SqlSession session) {
		int result =session.selectOne("NoticeMapper.selectListCount");
		return result;
	}

	@Override
	public List<Notice> searchBySubject(SqlSession session, String searchKeyword) {
		List<Notice> nList = session.selectList("NoticeMapper.selectConditionSubject", searchKeyword);
		return nList;
	}

	@Override
	public List<Notice> searchContentByKeyword(SqlSession session, String searchKeyword) {
		List<Notice> sList = session.selectList("NoticeMapper.selectNoticeContent",searchKeyword);
		return sList;
	}

	@Override
	public List<Notice> searchWriterByKeyword(SqlSession session, String searchKeyword) {
		List<Notice> sList = session.selectList("NoticeMapper.selectWriterByKeyword",searchKeyword);
		return sList;
	}

	@Override
	public List<Notice> searchAllByKeyword(SqlSession session, String searchKeyword) {
		List<Notice> sList = session.selectList("NoticeMapper.selectAllData",searchKeyword);
		return sList;
	}

	@Override
	public List<Notice> searchByKeyword(SqlSession session,Map<String,String> paraMap ,PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<Notice> sList = session.selectList("NoticeMapper.selectAllDataKeyword",paraMap,rowBounds);
		
		return sList;
	}

	@Override
	public int searchforMap(SqlSession session, Map<String, String> paraMap) {
		int result = session.selectOne("NoticeMapper.selectListCount", paraMap);
		return result;
	}

	@Override
	public Notice detailNotice(SqlSession session,int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectNoticeBynoticeNo",noticeNo);
		return notice;
	}

	@Override
	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.updateNotice",notice);
		return result;
	}
	
}
