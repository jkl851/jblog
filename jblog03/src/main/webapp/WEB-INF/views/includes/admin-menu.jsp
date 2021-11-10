<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
window.onload = function() {
var url = location.href.split('/')
var nameOfLis = ['admin', 'category', 'write'];
var lis = document.getElementsByClassName("admin-menu")[0].getElementsByTagName("li");

for(var i=0; i< nameOfLis.length; i++) {
	if (url[url.length-1] == nameOfLis[i]) {
		lis[i].className = "selected";
	}
  }
}
</script>

	<ul class="menu">
		<ul class="admin-menu">
			<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin">기본설정</a></li>
			<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/category">카테고리</a></li>
			<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/write">글작성</a></li>
		</ul>
	</ul>

