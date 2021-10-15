<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.contents, newline, "<br/>") }	
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<c:choose>
						<c:when test="${ authUser.no == vo.userNo }">
							<a href="${pageContext.request.contextPath }/board?a=list">글목록</a>
							<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${vo.no }">글수정</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/board?a=list">글목록</a>
						</c:otherwise>
					</c:choose>
				</div>
				
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board">
					<input type = "hidden" name = "a" value="writeReply">
					<input type = "hidden" name = "group" value="${vo.groupNo }">
					<input type = "hidden" name = "order" value="${vo.orderNo }">
					<input type = "hidden" name = "no" value="${vo.no }">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">댓글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="reply" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>