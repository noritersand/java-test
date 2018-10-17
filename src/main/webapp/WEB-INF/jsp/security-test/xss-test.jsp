<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/include/head.jsp"/>
<title>XSS test</title>
<script>
</script>
</head>
<body>
<h1>XSS 테스트</h1>
<div>
	<h2>파라미터로 받은 문자열을 그대로 출력했을 때</h2>
	<h3>test 1: scriptlet expression</h3>
	<p>&lt;%=request.getParameter("param1")%&gt; <%=request.getParameter("param1")%></p>
	<h3>test 2: jstl expression language</h3>
	<p>&#36;&#123;param.param1&#125; ${param.param1}</p>
	<p>결론: 스크립트가 실행된다.</p>
</div>
<div>
	<h2>파라미터로 받은 문자열을 input 태그의 value에 할당했을 때</h2>
	<form method="post" action="/security-test/xss-test.data">
		<h3>test 1: scriptlet expression</h3>
		<p><input type="text" name="first" value="<%=request.getParameter("param1")%>"></p>
		<h3>test 2: jstl expression language</h3>
		<p><input type="text" name="second" value="${param.param1}"></p>
		<p><button type="submit">보내기</button>
	</form>
	<p>결론: 스크립트 실행은 안되지만 value에 그대로 들어가며 서버에도 그대로 전달됨.</p>
</div>
</body>
</html>