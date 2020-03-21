<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

	<div id="menu">
		<ul class="menu">
			<c:if test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath }/">블로그 관리</a></li>
			</c:if>
			<c:if test="${not empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath }/${authUser.id }">내 블로그</a></li>
			</c:if>		
		</ul>
		</div>