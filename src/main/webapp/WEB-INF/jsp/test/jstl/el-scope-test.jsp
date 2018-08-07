<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="/static/js/jquery-1.12.4.js"></script>
<title>scope test</title>
</head>
<body>
<h2>expression language scope test</h2>
<p>아래 버튼을 누르고 페이지 새로고침 해보면...</p>
<button type="button" onclick="setAttributes()">setAttributes</button><br>
pageScope.a: ${pageScope.a}<br>
requestScope.b: ${requestScope.b}<br>
sessionScope.c: ${sessionScope.c}<br>
applicationScope.d: ${applicationScope.d}<br>
<h4>설명:</h4>
<ul>
	<li>pageScope.a는 설정하지 않았으므로 나오지 않음</li>
	<li>requestScope.b는 매 요청마다 초기화되므로 나오지 않음</li>
	<li>sessionScope.c는 브라우저를 닫을때까지 계속 나옴</li>
	<li>applicationScope.d는 서버 내려갈때까지 계속 나옴</li> 	
</ul>
<script>
	function setAttributes() {
		$.post('/test/scope/setAttributes.data', function(response) {
			console.log(JSON.stringify(response));
		});
	}
</script>
</body>
</html>