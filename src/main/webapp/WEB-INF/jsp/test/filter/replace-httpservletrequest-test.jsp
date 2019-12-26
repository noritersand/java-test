<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/include/head.jsp"/>
<title>HttpServletRequest 구현부 교체 테스트</title>
<script>
</script>
</head>
<body>
<h1>HttpServletRequest 구현부 교체 테스트</h1>
<div>
	<form method="post" action="/test/filter/replace-httpservletrequest.data">
		<input type="text" class="medium" name="first" value="유후후?" >
		<input type="text" class="medium" name="first" value="유후후?" >
		<input type="text" class="medium" name="second" value="유후?" >
		<button type="submit">날 눌러요 베이비</button>
	</form>
</div>
</body>
</html>