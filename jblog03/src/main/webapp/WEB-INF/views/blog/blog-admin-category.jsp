<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css" />
<style type="text/css">
img .btn-delete {
	src: url('${pageContext.request.contextPath}/assets/images/delete.jpg');
    background-position:  0px 0px;
    background-repeat: no-repeat;
    font-size: 0;
	overflow: hidden;
    width: 13px;
    height: 13px;
    border: 0px;
 	cursor:pointer;
 	outline: 0;
}
</style>
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		console.log('코드 시작');
		var listItemTemplate = new EJS({
			url: "${pageContext.request.contextPath }/assets/js/ejs/listitem-template.ejs"
		});
		var listTemplate = new EJS({
			url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
		});

	
		/// 카테고리 리스트 가져오기
		var catList = function() {
			console.log('ajax 실행')
			$.ajax({
				url: '${pageContext.request.contextPath }/api/${authUser.id }/admin/category',
				async: true,
				type: 'get',
				dataType: 'json',
				data: '',
				success: function(response){
					if(response.result != "success"){
						console.error(response.message);
						return;
					}
					var html = listTemplate.render(response);
					$(".admin-cat tbody").append(html);
				},
				error: function(xhr, status, e){
					console.error(status + ":" + e);
				}
			})
		} 

		
		$(function(){
			$('.cat-form').submit(function(event){
				event.preventDefault();
				var vo = {};
				vo.name = document.getElementsByName("name")[0].value
				if(vo.name == '미분류'){
					alert('미분류를 카테고리명으로 지정할 수 없습니다.');
					return
				}
				vo.description = document.getElementsByName("desc")[0].value
				if(vo.description == ''){
					vo.description = '내용없음';
				}
				console.log(vo)

				
				 $.ajax({
	                 url: '${pageContext.request.contextPath }/api/${authUser.id }/admin/category',
	                 dataType: "json",
	                 type: "post",
	                 contentType: "application/json",
	                 data: JSON.stringify(vo),
	                 success: function (response) {
	                     var html = listItemTemplate.render(response);
	                     $(".admin-cat tbody").append(html);
	                 },
	                 error: function (xhr, code, message) {
	                     console.error(message);
	                 }
	             });
	         });

		});
		
		
		// 카테고리 삭제 
		$(document).on('click', '.btn-delete', function(event){
				event.preventDefault();
				var _this = this;
				var no = $(this).find("input").val() //카테고리 넘버
				$.ajax({
	                 url: '${pageContext.request.contextPath }/api/${authUser.id }/admin/category/del/' + no,
	                 dataType: "json",
	                 type: "post",
	                 contentType: "application/json",
	                 data: "no=" + no,
	                 success: function (response) {
	                	 
	                     if(response.data != -1){
	                    	 	console.log('삭제리로드  ' + response.data)
	                    	 	console.log('태그확인  ' + $(".admin-cat tbody[data-no=" + response.data + "]"))
								$("#admin-cat tr[data-no=" + response.data + "]").remove();
							} 
	                 },
	                 error: function (xhr, code, message) {
	                     console.error(message);
	                 }
	             });
			});
		catList();
	</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<a href="${pageContext.request.contextPath }/${authUser.id }">
			<h1>${imgTitleVo.title }</h1></a>
			<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" />
		      	<table class="admin-cat" id="admin-cat">
		      			<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		
						
						<!-- Ajax로 받은 list 위치 -->
					
				  
				</table>

      			<h4 class="n-c">새로운 카테고리 추가</h4>
				<form class="cat-form" id="cat-form" method="post" action="">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input id="input-name" type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input id="input-desc" type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>  
		      		</table> 
		      	</form>  
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>