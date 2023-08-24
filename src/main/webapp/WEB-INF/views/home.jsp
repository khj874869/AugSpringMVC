<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<c:if test="${memberId ne null }">
			${memberName }님 환영합니다.
			<a href="/member/logout.kh">로그아웃</a> <br>
		</c:if>
	<c:if test="${memberId eq null }">
	<form action="/member/login.kh" method="post">
				<fieldset>
				<legend>로그인</legend>
				<input type="text"  name="memberId"><br>
				<input type="password" name="memberPw"><br>
				<input type="submit" value="로그인">
				<input type="reset"value="초기화">
				</fieldset>				
				</form>
		<form action="/member/myinfo.kh" method="post">
		<%-- 	<input type="hidden" name="memberId" value="${memberId }"> --%>
			<input type="submit" value="마이페이지"> 
		</form>
			<a href="/member/myinfo.kh">마이페이지</a>
		</c:if>
</body>
</html>
