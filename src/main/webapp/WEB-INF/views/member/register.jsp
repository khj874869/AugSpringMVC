<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>

<body>
	<form action="/member/register.kh" method="post">
	<fieldset>
	<legend>회원가입</legend>
	<ul>
		<li>
		<label>아이디</label>
		<input type="text" name="memberId">
		</li>
		<li>
		<label>패스워드</label>
		<input type="password" name="memberPw">
		</li>
		<li>
		<label>이름</label>
		<input type="text" name="memberName">
		</li>
		<li>
		<label>나이</label>
		<input type="text" name="memberAge">
		</li>
		<li>
			<label>성별</label>
		남<input type="radio" name="memberGender" value="M">	
		여<input type="radio" name="memberGender" value="F">
		</li>
		<li>
		<label>이메일</label>
		<input type="text" name="memberEmail">
		</li>
		<li>
		<label>전화번호</label>
		<input type="text" name="memberPhone">
		</li>
		<li>
		<label>주소</label>
		<input type="text" name="memberAddress"> 
		<input type="button" onclick="sample4_execDaumPostcode();" value="우편번호">
		</li>
		<li>
		<label>취미</label>
		<input type="text"name="memberHobby"> 
		</li>	
	</ul>
	</fieldset>
	<input type="submit" value="가입">
	<input type="reset" value="초기화">
	</form>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script >
	function sample4_execDaumPostcode(){
		new daum.Postcode({
			oncomplete : function(data){
				doucument.querySelector("memberAddress")
				.value = data.zonecode+","+data.autoJibunAddress+","+data.BuildingCode
			}
			
		}).open();
		
	}
	</script>
</body>

</html>