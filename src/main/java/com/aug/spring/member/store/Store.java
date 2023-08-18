package com.aug.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.aug.spring.member.domain.Member;

public interface Store {

	int insertMember(SqlSession session, Member member);

	int selectOneById(SqlSession session, Member member);

	Member checkMember(SqlSession session,String memberId);

	int deleteMember(SqlSession session, String memberId);

	int updateMember(SqlSession session, Member member);

}
