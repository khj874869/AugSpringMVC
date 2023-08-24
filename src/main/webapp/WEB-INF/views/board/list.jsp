<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<link rel="stylesheet" href="../resources/css/notice.css">
<body>

<h1>게시판 목록</h1>
	<table>
		<colgroup>
			<col></col>
			<col></col>
			<col></col>
			<col></col>
			<col></col>
		</colgroup>
		<thead>
			<tr>
				<th width="5%">번호</th>
				<th width="40%">제목</th>
				<th width="10%">작성자</th>
				<th width="15%">작성날짜</th>
				<th width="10%">첨부파일</th>
			</tr>
		</thead>
		<tbody>
		<!-- list데이터는 items에 넣었고 var에서 설정한 변수로 list데이터에서  -->
		<!-- 꺼낸 값을 사용하고 i의 값은 varStatus로 사용한다.  -->
			<c:forEach var="board" items="${bList }" varStatus="i">
				<tr>
					<td>${i.count }</td>
					<c:url var="detailUrl" value="/board/detail.kh">
						<c:param name="boardNo" value="${board.boardNo}"></c:param>
					</c:url>
					<td><a href="${detailUrl}" > ${board.boardTitle }</a></td>
					<td>${board.boardWriter }</td>
					<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${board.bCreateDate }"/>
					<!--${notice.nCreateDate} 시/분/초 필요 없을시-->
					</td>
					<td>
						<c:if test="${!empty board.boardFilename }">O</c:if>
						<c:if test="${empty board.boardFilename }">X</c:if>
					</td>
				<%-- 	<td>
						<fmt:formatNumber pattern="##,###,###" value="123000"></fmt:formatNumber>
					</td> --%>
				</tr>
			</c:forEach>
				
		</tbody>
		<tfoot>
			<tr align="center">
				<td colspan="5">
				<c:if test="${pInfo.startNavi !=1 }">
					<c:url var="prevUrl" value="/board/list.kh">
						<c:param name="page" value="${pInfo.startNavi-1 }"></c:param>
					</c:url>
					<a href="${prevUrl }">[이전]</a>
					</c:if>
				<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
					<c:url var="pageUrl" value="/board/list.kh">
						<c:param name="page" value="${p }"></c:param>
					</c:url>
					<a href="${pageUrl }">${p }</a>&nbsp;
				</c:forEach>
				<c:if test="${pInfo.endNavi != pInfo.naviTotelCount }">
					<c:url var="postUrl" value="/board/list.kh">
					<c:param name="page" value="${pInfo.endNavi+1 }"></c:param>
					</c:url>
				<a href="${postUrl }">[다음]</a>
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="4">
		<form action="/board/search.kh" method="get">
			<select name="searchCondition">
				<option value="all">전체</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>			
				<input type="text" name="searchKeyword"placeholder="검색어를 입력하세요.">
				<input type="submit" value="검색">
			</form>	
			</td>
			<td>
				<button>글쓰기</button>
			</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>