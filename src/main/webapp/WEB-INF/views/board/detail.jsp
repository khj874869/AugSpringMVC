<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항등록</title>
<link rel="stylesheet" href="../resources/css/main.css">
<link rel="stylesheet" href="../resources/css/board.css">
</head>
<body>
 	<h1>상세표</h1>
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
 		<br><br>
 		<c:url var="boardDelUrl" value="/board/delete.kh">
 			<c:param name="boardWriter" value="${board.boardWriter }"></c:param>
 			<c:param name="boardNo" value="${board.boardNo}" ></c:param>
 		</c:url>
 		<c:url var="boardModifyUrl" value="/board/modify.kh">
 			<c:param name="boardWriter" value='${board.boardWriter }'></c:param>
 			<c:param name="baordNo" value='${board.boardNo }'></c:param>
 		</c:url>
 		</form>
 		<div>
 			<c:if test="${board.boardWriter eq memberId} ">
				<button type="button" onclick="showModifyPage('${boardMofiyUrl}');">수정하기</button>
				<button type="button" onclick="deleteBoard('${boardDelUrl}');">삭제하기</button>
				<button type="button" onclick="showNoticeList();">목록으로</button>
			</c:if>
		</div>
			<!-- 댓글 등록 -->
			<hr>
			<form action="/reply/add.kh" method="post">
				<input type="hidden" name="refBoardNo" value="${board.boardNo }">
				<table width="500" border="1">
					<tr>
						<td>
							<textarea rows="3" cols="55" name="replyContent"></textarea>
						</td>
						<td>
							<input type="submit" value="완료">
						</td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="550" border="1">
				<c:forEach var="reply" items="${rList }">
					<tr>
						<td>${reply.replyWriter }</td>
						<td>${reply.replyContent }</td>
						<td>${reply.rCreateDate }</td>
						<td>
							<a href="javascript:void(0);" onclick="showReplyModifyForm(this,'${reply.replyContent}');">수정하기</a>
							<c:url var="delUrl" value="/reply/delete.kh">
								<c:param name="replyNo" value="${reply.replyNo }"></c:param>
								<!-- 성공하면 디테일로 가기위해 필요한 boardNo 셋팅 -->
								<c:param name="refBoardNo" value="${reply.refBoardNo }"></c:param>
							</c:url>
							<a href="javascript:void(0)" onclick="deleteReply('${delUrl }');")>삭제하기</a>
						</td>
					</tr>
					<tr id="replyModifyForm" style="display:none;">
<!-- 						<form action="/reply/update.kh" method="post"> -->
<%-- 							<input type="hidden" name="replyNo" value="${reply.replyNo }"> --%>
<%-- 							<input typeC="hidden" name="refBoardNo" value="${reply.refBoardNo }"> --%>
<%-- 							<td colspan="3"><input type="text" size="50" name="replyContent" value="${reply.replyContent }"></td> --%>
<!-- 							<td><input type="submit" value="완료"></td> -->
<!-- 						</form> -->
							<td colspan="3"><input id="replyContent" type="text" size="50" name="replyContent" value="${reply.replyContent }"></td>
							<td><input type="button" onclick="replyModify(this,'${reply.replyNo}','${reply.refBoardNo }');" value="완료"></td>
					</tr>
				</c:forEach>
			</table>
	
 		
 	<script >
 	const showModifyPage=(boardModify)=>{
 		loaction.href=boardModifyUrl;
 	}
 	const deleteBoard=(boardDelUrl)=>{
 		location.href=boardDelUrl;
 	}
 	function deleteReply(delUrl){
 		location.href=delUrl;
 	}
 		function showModifyPage(){
 			const boardNo = "${board.boardNo}";
 			location.href="/board/modify.kh?boardNo="+boardNo;
 		}
 		
 		function showNoticeList(){
 			loction.href="/board/list.kh";
 		}
 		function modifyReply(obj,replyNo,replyBoardNo){
 			// DOM 프로그래밍을 이용하는 방법
 			const form = document.createElement("form");
 			form.action="/reply/update.kh";
 			form.method="post";
 			const input = document.createElement("input");
 			input.type="hidden";
 			input.value="12312";
 			input.name="replyNo";
 			const input2 = document.createElement("input");
 			input2.type="hidden";
 			input2.value="123123";
 			input2.name="refBoardNo"; 			
 			const input3 = document.createElement("input");
 			input3.type="text";
 			//input3.value=document.querySelector("#replyContent").value;
 			input3.value= obj.parentElement.previousElementSibling.childNodes[0].value;
 			input3.name="replyContent";
 			form.appendChild(input);
 			form.appendChild(input2);
 			form.appendChild(input3);
 			doucment.body.appendChild(form);
 			form.submit();
 		}
 		function showReplyModifyForm(obj,replyConmment){
 			obj.parentElement.parentElement.nextElementSibling.style.display="";
 			//2. DOM프로그래밍을 이용하는 방법
 			 <tr id="replymodifyform"  style="display:none;">
			<td colspan="3"><input type="text" size=20></td>
			<td><input type="button" value="완료"></td>
			</tr>  
 			/* const trTag = document.createElement("tr");
			const tdTag1 = document.createElement("td");
			tdTag1.colSpan =3;
			const inputTag1 = document.createElement("input");
			input.type="text";
			input.size=50;
			input.value=replyConmment;
			tdTag1.appendChild(inputTag);
			const tdTag2 = document.createElement("td");
			const inputTag2 = document.createElement("input");
			inputTag2.Type="button";
			inputTag2.value="완료";
			tdTag2.appendChild(inputTag2);
			const prevTrTag = obj.parentElement.parentElement;
			if(!prevTrTag.nextElementSibling.querySelector("input"))
		    prevTrTag.parentMode.InsertBefore(trTag,prevTrTage.nextSibling); */
 			//1.HTML태그,displayname 사용하는 방법
 	//		documentquerySelector("#replyModifyForm").style.display="";
 //			obj.parentElement.parentElement.nextElementSibling.style.display="";
 		}
 	</script>
</body>
</html>