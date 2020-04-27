<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="/WEB-INF/jsp/include/head.jsp"/>
<title>HTTP 응답 코드 테스트</title>
<script>
</script>
</head>
<body>
<h1>HTTP 응답 코드 테스트</h1>
<section>
	<h4>301 moved permanently</h4>
	<form method="post" action="/page/http/let-me-301-moved-permanently.data">
		<input type="text" class="small" name="Homer-simpson-saying" value="D'oh!" >
		<button type="submit">SUBMIT</button>
	</form>
	<h4>302 found</h4>
	<form method="post" action="/page/http/let-me-302-found.data">
		<input type="text" class="small" name="Homer-simpson-saying" value="D'oh!" >
		<button type="submit">SUBMIT</button>
	</form>
</section>
<section>
	<h4>307 temporary redirect</h4>
	<form method="post" action="/page/http/let-me-307-temporary-redirect.data">
		<input type="text" class="small" name="Homer-simpson-saying" value="D'oh!" >
		<button type="submit">SUBMIT</button>
	</form>
	<h4>308 permanent redirect</h4>
	<form method="post" action="/page/http/let-me-308-permanent-redirect.data">
		<input type="text" class="small" name="Homer-simpson-saying" value="D'oh!" >
		<button type="submit">SUBMIT</button>
	</form>
</section>
</body>
</html>
