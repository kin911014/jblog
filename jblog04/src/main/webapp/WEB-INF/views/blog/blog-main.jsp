<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${mainPost.title }</h4>
					<p>${fn:replace(mainPost.contents, newLine,"<br>") }<p>
				</div>
				<ul class="blog-list">
					<c:forEach items='${postList }' var='postvo' varStatus='status'>
						<li>
							<a href="${pageContext.request.contextPath }/${blogVo.id }/${postvo.categoryNo }/${postvo.no }">${postvo.title }</a>
							<span>${fn:substring(postvo.regDate,0,11) }</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/images/${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<c:forEach var="categoryvo" items="${getValues }" varStatus="status">
			<ul>
				<li><a href="${pageContext.request.contextPath}/${blogVo.id}/${categoryvo.no}">${categoryvo.name }</a></li>
				<!-- <li><a href="">스프링 스터디</a></li>
				<li><a href="">스프링 프로젝트</a></li>
				<li><a href="">기타</a></li> -->
			</ul>
			</c:forEach>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>