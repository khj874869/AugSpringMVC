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
 <form action="/board/modify.kh" method="post" enctype="multipart/form-data">
<!--  	수정할때, 리다이렉트 될 때 사용 --> 	
	<input type="hidden" name="boardNo" value="${board.boardNo }">
<!-- 	기존 업로드 파일 체크할 때 사용 --> 	
	<input type="hidden" name="boardFilename" value="${board.boardFilename }">
	<input type="hidden" name="boardFilerename" value="${board.boardFilerename }">
	<input type="hidden" name="boardFilepath" value="${board.boardFilepath }">
	<input type="hidden" name="boardFilelength" value="${board.boardFilelength}">
	<fieldset>
		<legend>공지사항 작성</legend>
			<ul>
 			<li>
 				<label>제목</label>
 				<input type="text" name="boardTitle" value="${board.boardTitle }" >
 			</li>
 			<li>
 				<label>작성자</label>
 				<input type="text" name="boardWriter" value="${board.boardWriter }">
 			</li>
 			<br>
 			<li>
 				<label>내용</label>
				<textarea rows="7" cols="100" name="boardContent" value="${board.boardContent }"></textarea>
 			</li>
 			<li>
 				<label>첨부파일</label>
 			<img alt="첨부파일" src="../resources/nuploadFiles/${board.boardFilename }">			
			<a href="../resources/nuploadFiles/${board.boardFilerename }" download>${board.boardFilename }</a>
 			<input type="file" name="uploadFile">
 			</li>
 		</ul>
 		<div>
 			<input type="submit" value="수정하기">
 			<button type="button" onclick="showBoardList();">목록으로</button>
 			<button type="button" onclick="javascript:hitsory.go(-1);">뒤로가기</button>
 			<button>삭제하기</button>
 		</div>
 		
 		</fieldset>
	</form>
 	<script >
 		function showBoardList(){
 			const boardNo = "${board.boardNo}";
 			location.href="/board/list.kh?boardNo="+boardNo;
 		}
 	</script>
</body>
</html>