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

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">
var startNo = 0;
var isEnd = false;
$(function(){
	$('#btn-test').click(function(event){
		event.preventDefault();
		console.log('click');	
		
		
		$.ajax({
			url: '${pageContext.request.contextPath }/${authUser.id}/api/blog/list/' + startNo,
			async: true,
			type: 'get',
			dataType: 'json',
			data: '',
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
					return;
				}		
				
				// rendering
				$.each(response.data, function(index, vo){
				var html = 
					"<tr>" + 
					"<td>" + vo.no + "</td>" +
					"<td>" + vo.name + "</td>" +
					"<td>" + vo.postCount + "</td>" +
					"<td>" + vo.description + "</td>" + 
					"<td>" +
					"</td>";
					
					$("#list-category").append(html);
				});
				
				//startNo = ...
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});
	});
});

</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" />
		      	<table id='list-category' class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					
					<c:forEach var="getValue" items="${getValues }" varStatus="status">
					
					<!-- <tr> -->
						<%-- <td>${getValue.no }</td>
						<td>${getValue.name }</td>
						<td>${getValue.postCount }</td>
						<td>${getValue.description }</td>
						<td>
						<c:if test="${getValue.postCount == 0 }">
						
							<a href="${pageContext.request.contextPath}/${getValue.id }/blog-admin-category/delete/${getValue.no}">
							<img src="${pageContext.request.contextPath}/assets/image/delete.jpg">
						</a>
						</c:if>
						</td> --%>
					<!-- </tr>	 -->				  
					</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
				
	      		   	<form action="${pageContext.request.contextPath }/${id }/blog-admin-category" method="post">
				      	<table id="admin-cat-add">
				      		<tr>
				      			<td class="t">카테고리명</td>
				      			<td><input type="text" name="name"></td>
				      		</tr>
				      		<tr>
				      			<td class="t">설명</td>
				      			<td><input type="text" name="description"></td>
				      		</tr>
				      		<tr>
				      			<td><input type="submit" value="카테고리 추가"></td>
				      			<td><input type="submit" id='btn-test' value="테스트버튼"></td>
				      		</tr>
				      		
				      	</table> 
	      			</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>