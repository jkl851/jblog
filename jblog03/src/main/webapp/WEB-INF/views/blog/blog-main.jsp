<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blogVo.title }</title>
<script>
/*
 	게시글 조작
*/
/*
window.addEventListener("load", function(){
	var postli = document.getElementById('to-content');
	document
	.getElementById("to-content")
	.addEventListener("click", function(event){
		event.preventDefault();
		
		console.log(postli.innerText);
	});
});
*/
</script>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<c:choose>
				<c:when test='${not empty post }'>
					<h4>${post.title }</h4>
					<p>
						${post.contents }
					<p>
				</c:when>
				<c:otherwise>
					<h4>${postList[0].title }</h4>
					<p>
						${postList[0].contents }
					<p>
				</c:otherwise>
				</c:choose>
				</div>
				<ul class="blog-list">
				<c:forEach items='${postList }' var='postvo' varStatus='status'>
					<li><a id="to-content" href="${pageContext.request.contextPath }/${blogVo.id }/${postvo.catNo }/${postvo.postNo }">${postvo.title }</a> 
					<span>${postvo.reg_date }</span></li>
				</c:forEach> 
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items='${catList }' var='categoryvo' varStatus='status'>
				<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${categoryvo.no }">${categoryvo.name }(${categoryvo.postCounts })</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>