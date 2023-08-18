<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/member/register.kh" method="post">
	<fieldset>
	<legend>회원가입</legend>
			<ul>
				<li>
				<label>아이디</label> 
				<span>${member.memberId}</span>
				</li>
				<li>
				<label>패스워드</label> 
				<span>${member.memberPw}</span>
				</li>
				<li>
				<label>이름</label> 
				<span>${member.memberName}
				</span>
				</li>
				<li>
				<label>나이</label> 
				<span>${member.memberAge}</span>
				</li>
				<li>
				<label>성별</label> 
				<span>${member.memberGender}</span>
				</li>
				<li>
				<label>이메일</label>
				 <span>${member.memberEmail}</span>
				 </li>
				<li>
				<label>전화번호</label>
				 <span>${member.memberPhone}</span>
				 </li>

				<li>
				<label>주소</label>
				 <span>${member.memberAddress}</span>
				 </li>
				<li>
				<label>취미</label>
				 <span>${member.memberHobby}</span>
				 </li>
			</ul>
		</fieldset>
	<a href="/index.jsp">홈으로 이동</a>
	<a href="/member/update.kh?memberId=${member.memberId }">수정하기 </a>
	<a href="/member/delete.kh?memberId=${member.memberId }">삭제하기 </a>
	</form>
</body>
</html>