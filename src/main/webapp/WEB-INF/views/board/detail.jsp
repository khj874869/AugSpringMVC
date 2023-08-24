<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항등록</title>
<link rel="stylesheet" href="../resources/css/main.css">
<link rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
 	<h1>공지 등록</h1>
 	<form action="/board/detail.kh" method="post" enctype="multipart/form-data">
 		<ul>
 			<li>
 				<label>제목</label>
 				<input type="text" name="boardTitle" value="${board.boardTitle }" readonly>
 			</li>
 			<li>
 				<label>작성자</label>
 			<span>"${board.boardWriter }"</span>
 			</li>
 			<li>
 				<label>내용</label>
				<p>"${board.boardContent }"></p>
 			</li>
 			<li>
 				<label>첨부파일</label>
 				<!-- String으로 받을 수 없고 변환하는 작업이 필요하다. -->
				<img alt="첨부파일" src="../resources/nuploadFiles/${board.boardFilename }">			
			<a href="../resources/buploadFiles/${board.boardFilename }" download>${board.boardFilename }</a>
 			</li>
 		
 		</ul>
 		<div>
 			<button type="button" onclick="showModifyPage();">수정하기</button>
			<button>삭제하기</button>
 		</div>
 		<!-- 댓글등록 -->
 		<form action="/board/addreply.kh" method="post">
 		<table align="left" width="500px" border=1>
 		<tr >
		<td>
		<textarea rows="3" cols="55"></textarea>
		<input type="submit" value="완료">		
 		</td>
 		</table>
 		</form>
 		<!-- 댓글목록 -->
		<table  align="left" width="500px" border=1>
		<tr>
			<td>일용자</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>
			<a href="#">수정하기</a>
			<a href="#">삭제하기</a>
			</td>
		</tr>
			<td>일용자</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>
			<a href="#">수정하기</a>
			<a href="#">삭제하기</a>
			</td>
		</tr>
			<td>일용자</td>
			<td>2</td>
			<td>3</td>
			<td>4</td>
			<td>
			<a href="#">수정하기</a>
			<a href="#">삭제하기</a>
			</td>
		</tr>
		</table>
 		
 	</form> 	
 	<script >
 		function showModifyPage(){
 			const boardNo = "${board.boardNo}";
 			location.href="/board/modify.kh?boardNo="+boardNo;
 		}
 	</script>
</body>
</html>