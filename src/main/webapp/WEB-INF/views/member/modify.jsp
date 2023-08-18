<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>회원수정</title>
	<link rel="stylesheet"href="../resources/css/main.css">
	</head>
	<body>
		<h1>회원수정</h1>
		<form action="/member/modify.kh"method="post">
		<fieldset>
		<legend>회원수정</legend>
		<ul>
		<li>
		<label>아이디</label>
		<input type="text" name="memberId" value="${memberId }">
		<label>패스워드</label>
		<input type="text"name="memberPw" value="${memberPw }">
		</li>
		<li>
		<label>이름</label>
		<input type="text"name="memberName"value="${memberName }">
		</li>
		<li>
		<label>성별</label>
		<c:if test="${member.memberGender eq 'M' }" ><span>남</span> </c:if>
		<c:if test="${member.memberGender eq 'F'} "><sapn>여</sapn></c:if>
		<input type="text"name="memberName">
		</li>
		<li>
		<label>이메일</label>
		<input type="text"name="memberEmail"value="${memberEmail }">
		</li>
		<li>
		<label>주소</label>
		<input type="text"name="memberAddress"value="${memberAddress }">
		</li>
		<li>
		<label>취미</label>
		<input type="text"name="memberHobby"value="${memberHobby }">
		</li>
		<li>
		</ul>
		</fieldset>
		<div>
		<input type="submit" value="제출">
		<input type="reset" value="취소">
		</div>
		</form>
	</body>
</html>