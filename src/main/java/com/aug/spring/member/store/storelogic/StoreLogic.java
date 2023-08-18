package com.aug.spring.member.store.storelogic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.aug.spring.member.domain.Member;
import com.aug.spring.member.store.Store;

@Repository
public class StoreLogic implements Store {

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember",member);
		return result;
	}

	@Override
	public int selectOneById(SqlSession session, Member member) {
		int result = session.selectOne("MemberMapper.selectCheckLogin",member);
		return result;
	}

	@Override
	public Member checkMember(SqlSession session, String memberId) {
		Member mOne = session.selectOne("MemberMapper.selectOneById", memberId);
		return mOne;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember", memberId);
		return result;
	}

	@Override
	public int updateMember(SqlSession session, Member member) {
		int result = session.delete("MemberMapper.updateMember", member);
		return result;
	}

}
