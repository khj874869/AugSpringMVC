<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h1>게시글 작성</h1>
	<form action="/board/write.kh" method="post" enctype="multipart/form-data">
	<fieldset>
		<legend>게시글 작성</legend>
			<ul>
 			<li>
 				<label>제목</label>
 				<input type="text" name="boardTitle" >
 			</li>
 			<li>
 				<label>작성자</label>
 				<input type="text" name="boardWriter">
 			</li>
 			<li>
 				<label>내용</label>
				<textarea rows="7" cols="100" name="boardContent"></textarea>
 			</li>
 			<li>
 				<label>첨부파일</label>
 				<input type="file" name="uploadFile">
 			</li>
 		
 		</ul>
 		<div>
 			<input type="submit" value="등록">
 		</div>
	</form>
</body>
</html>