<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항등록</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
 	<h1>공지 등록</h1>
 	<form action="/notice/detail.kh" method="post" enctype="multipart/form-data">
 		<ul>
 			<li>
 				<label>제목</label>
 				<input type="text" name="noticeSubject" value="${notice.noticeSubject }" readonly>
 			</li>
 			<li>
 				<label>작성자</label>
 			<span>"${notice.noticeWriter }"</span>
 			</li>
 			<li>
 				<label>내용</label>
				<p>"${notice.noticeContent }"></p>
 			</li>
 			<li>
 				<label>첨부파일</label>
 				<!-- String으로 받을 수 없고 변환하는 작업이 필요하다. -->
				<img alt="첨부파일" src="../resources/nuploadFiles/${notice.noticeFilename }">			
			<a href="../resources/nuploadFiles/${notice.noticeFilename }" download>${notice.noticeFilename }</a>
 			</li>
 		
 		</ul>
 		<div>
 			<button type="button" onclick="showModifyPage();">수정하기</button>
			<button>삭제하기</button>
 		</div>
 	</form>
 	<script >
 		function showModifyPage(){
 			const noticeNo = "${notice.noticeNo}";
 			location.href="/notice/modify.kh?noticeNo="+noticeNo;
 		}
 	</script>
</body>
</html>