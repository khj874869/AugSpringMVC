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
 <form action="/notice/modify.kh" method="post" enctype="multipart/form-data">
<!--  	수정할때, 리다이렉트 될 때 사용 --> 	
	<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
<!-- 	기존 업로드 파일 체크할 때 사용 --> 	
	<input type="hidden" name="noticeFilename" value="${notice.noticeFilename }">
	<input type="hidden" name="noticeFilerename" value="${notice.noticeFilerename }">
	<input type="hidden" name="noticeFilepath" value="${notice.noticeFilepath }">
	<input type="hidden" name="noticeFilelength" value="${notice.noticeFilelength}">
	<fieldset>
		<legend>공지사항 작성</legend>
			<ul>
 			<li>
 				<label>제목</label>
 				<input type="text" name="noticeSubject" value="${notice.noticeSubject }" >
 			</li>
 			<li>
 				<label>작성자</label>
 				<input type="text" name="noticeWriter" value="${notice.noticeWriter }">
 			</li>
 			<br>
 			<li>
 				<label>내용</label>
				<textarea rows="7" cols="100" name="noticeContent" value="${notice.noticeContent }"></textarea>
 			</li>
 			<li>
 				<label>첨부파일</label>
 			<img alt="첨부파일" src="../resources/nuploadFiles/${notice.noticeFilename }">			
			<a href="../resources/nuploadFiles/${notice.noticeFilerename }" download>${notice.noticeFilename }</a>
 			<input type="file" name="uploadFile">
 			</li>
 		</ul>
 		<div>
 			<button type="button" onclick="showModifyPage();">수정하기</button>
 			<button>삭제하기</button>
 		</div>
 		
 		</fieldset>
	</form>
 	<script >
 		function showModifyPage(){
 			const noticeNo = "${notice.noticeNo}";
 			location.href="/notice/modify.kh?noticeNo="+noticeNo;
 		}
 	</script>
</body>
</html>