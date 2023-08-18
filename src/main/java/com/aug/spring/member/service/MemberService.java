package com.aug.spring.member.service;

import com.aug.spring.member.domain.Member;

public interface MemberService {

	int registerMember(Member member);

	int selectOneById(Member member);

	Member checkedMember(String memberId);

	int deleteMember(String memberId);

	int updateMember(Member member);




}
