<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/include/head.jsp"/>
<title>HTTP 응답 코드 테스트</title>
<script>
</script>
</head>
<body>
<h1>HTTP 응답 코드 테스트</h1>
<section>
	<h4>307</h4>
	<form method="post" action="/http-test/let-me-307-redirect.data">
		<input type="text" class="size-s" name="Homer-simpson-saying" value="D'oh!" >
		<button type="submit">SUBMIT</button>
	</form>
</section>
<section>
	<h4>302</h4>
	<form method="post" action="/http-test/let-me-302-found.data">
		<input type="text" class="size-s" name="Homer-simpson-saying" value="D'oh!" >
		<button type="submit">SUBMIT</button>
	</form>
</section>
</body>
</html>
