<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>

<script type="text/javascript">
var isEnd = false;

var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});

var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-item-template.ejs"
})



var list = function(){
	if(isEnd){
		return;	
	}
		
	$.ajax({
		url: '${pageContext.request.contextPath }/${authUser.id}/api/blog/list/',
		async: true,
		type: 'get',
		dataType: 'json',
		data: '',
		success: function(response){
			if(response.result != "success"){
				console.error(response.message);
				return;
			}
			console.log(response);
			response.contextPath = "${pageContext.request.contextPath }";
			
			var html = listTemplate.render(response);
			$("#list-category").append(html);
			
			startNo = $('#list-category td').last().data('no') || 0;
			
			//startNo = ...
		},
		error: function(xhr, status, e){
			console.error(status + ":" + e);
		}
	});
	
};

$(document).on('click', '#list-category a', function(event){
	event.preventDefault();
	console.log($(this).data('no'));
	
	var deleteNo = $(this).data('no');
	$(this).parents('tr').remove();
	
	$.ajax({
		url: '${pageContext.request.contextPath }/${authUser.id}/api/blog/delete/' + deleteNo,
		async: true,
		type: 'delete',
		dataType: 'json',
		data: '',
		success: function(response){
			if(response.result != "success"){
				console.error(response.message);
				return;
			}
		},
		error: function(xhr, status, e){
			console.error(status + ":" + e);
		}
	});
	
});

$(function(){
	
	// 입력 submit event
	$('#submit-form').submit(function(event){
		event.preventDefault();
		console.log("click!");
		
		var vo={};
		vo.name = $('#input-name').val();
		vo.description = $('#input-desc').val();
		
		
		$.ajax({
			url: '${pageContext.request.contextPath }/${authUser.id}/api/blog/add/',
			async: true,
			type: 'post',
			dataType: "json",
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
				 	return;
				} 
				console.log(response.data);
				
				response.data.contextPath = "${pageContext.request.contextPath }";
				
				// rendering
				var lastNum = Number($('#list-category tr:last-child td')[0].innerText)+ 1;
				response.data.lastNum = lastNum;
				var html = listItemTemplate.render(response.data);
				$("#list-category tr").last().after(html);
				
				// form reset
				$("#submit-form")[0].reset();
				
			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});
	});
	// 리스트 가져오기
	list();
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

				<form id='submit-form' action="" method="post">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" id='input-name' name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" id='input-desc' name="description"></td>
						</tr>
						<tr>
							<td><input  type="submit" value="카테고리 추가"></td>
						</tr>

					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>