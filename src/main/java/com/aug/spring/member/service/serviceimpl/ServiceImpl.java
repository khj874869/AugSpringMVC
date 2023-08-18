package com.aug.spring.member.service.serviceimpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aug.spring.member.domain.Member;
import com.aug.spring.member.service.MemberService;
import com.aug.spring.member.store.Store;

@Service
public class ServiceImpl implements MemberService {
@Autowired
private SqlSession session;
@Autowired  
private Store store;
	@Override
	public int registerMember(Member member) {
		int result = store.insertMember(session,member); 
		return result;
	}
	@Override
	public int selectOneById(Member member) {
		int result = store.selectOneById(session,member);
		return result;
	}
	@Override
	public Member checkedMember(String memberId) {
		Member mOne = store.checkMember(session,memberId);
		return mOne;
	}
	@Override
	public int deleteMember(String memberId) {
		int result = store.deleteMember(session,memberId);
		return result;
	}
	@Override
	public int updateMember(Member member) {
		int result = store.updateMember(session,member);
		return result;	
		}

}
