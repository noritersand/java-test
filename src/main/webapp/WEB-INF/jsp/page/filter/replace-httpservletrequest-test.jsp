<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/include/head-old.jsp"/>
<title>HttpServletRequest 구현부 교체 테스트</title>
<script>
</script>
</head>
<body>
<h1>HttpServletRequest 구현부 교체 테스트</h1>
<div>
	<form method="post" action="/page/filter/replace-httpservletrequest.data">
		<input type="text" class="medium" name="first" value="호옹이!" >
		<input type="text" class="medium" name="first" value="호호이" >
		<input type="text" class="medium" name="second" value="호오?" >
		<button type="submit">날 눌러요 베이비</button>
	</form>
</div>
</body>
</html>