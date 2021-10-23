<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board?" method="post">
					<input type = "hidden" name = "a" value="search">	
					<select name = "tp" style=' width: 15%;
									            padding: .2em .5em;
									            border-radius: 4px;' name='type'>
						<option value="t" >제목</option>
						<option value="w" >글쓴이</option>
						<option value="c" >내용</option>
						<option value="twc" >---</option>
					</select> 
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
						
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align:left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th></th>
					</tr>			
					<c:set var='count' value='${fn:length(list) }' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<c:choose>
							<c:when test="${vo.depth == 0 }">
								<tr>
									<td>${vo.resultNo }</td>
									<td style="text-align:left; padding-left:0px"><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a></td>
									<td>${vo.userName }</td>
									<td>${vo.hit }</td>
									<td>${vo.regDate }</td>
									<c:choose>
										<c:when test="${ authUser.no == vo.userNo }">
											<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }">삭제</a></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${vo.resultNo }</td>
									<td style="text-align:left; padding-left:${20*vo.depth }px"><img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' /><a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a></td>
									<td>${vo.userName }</td> 
									<td>${vo.hit }</td>
									<td>${vo.regDate }</td>
									<c:choose>
										<c:when test="${ authUser.no == vo.userNo }">
											<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }">삭제</a></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:forEach var="num" begin="${page.startPage }" end="${page.endPage }" >
							<c:choose>
								<c:when test="${page.pageNum == num}">
									<li class="selected"> ${num } </li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${page.realEnd >= num}">
											<li><a href="${pageContext.request.contextPath }/board?a=list&no=${num }">${num }</a></li>
										</c:when>
										<c:otherwise>
												<li>${num }</li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:choose>
						<c:when test="${empty authUser }">
							<a href="${pageContext.request.contextPath }/user?a=loginform" id="new-book">글쓰기</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
						</c:otherwise>
					</c:choose>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>