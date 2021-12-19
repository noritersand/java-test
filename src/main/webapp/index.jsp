<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>index</title>
<meta charset="utf-8">
<jsp:include page="/WEB-INF/jsp/include/head.jsp"/>
</head>
<body>
<div class="align-center">
	<h1>LABORATORY: Java testing</h1>
	<h2>Index</h2>
	<ul>
		<li><a href="/page/uncategorized/test-file-template.view">테스트 파일 템플릿.jsp</a></li>
		<li><a href="/page/test-file-template.html">테스트 파일 템플릿.html</a></li>
		<li>
			<h4>HTTP</h4>
			<ul>
				<li><a href="/page/http/response-status-code-test.view">HTTP 응답 코드</a></li>
			</ul>
		</li>
		<li>
			<h4>Security</h4>
			<ul>
				<li><a href="/page/security/xss-test.view?param1=">XSS</a></li>
			</ul>
		</li>
		<li>
			<h4>JAVA &amp; JSP &amp; Servlet</h4>
			<ul>
				<li><a href="/page/uncategorized/test-reponse-character-set.data">응답 문자셋</a></li>
				<li><a href="/page/uncategorized/submission-charset/sender-test.view">유니코드 문자 submit</a></li>
				<li>include
					<ul>
						<li><a href="/page/uncategorized/include/include-with-jsp-test.view">include test with jsp</a></li>
						<li><a href="/page/uncategorized/include/include-with-html-test.view">include test with html</a></li>
					</ul>
				</li>
				<li>파일 업로드
					<ul>
						<li><a href="/page/oreilly/oreilly-fileupload-test.view">oreilly multipart</a></li>
					</ul>
				</li>
				<li><a href="/page/uncategorized/what-is-payload-body-test.view">payload body란 무엇인가?</a></li>
				<li><a href="/page/uncategorized/session-invalidate-test.view">session invalidate</a></li>
				<li><a href="/page/uncategorized/customtag/parent-test.view">custom tag</a></li>
				<li><a href="/page/uncategorized/jstl/parse-number-from-string-test.view">parse number from string</a></li>
				<li><a href="/page/uncategorized/jstl/jstl-test.view">JSTL</a></li>
				<li><a href="/page/uncategorized/jstl/el-scope-test.view">EL: scope</a></li>
				<li><a href="/page/uncategorized/jstl/el-operator-test.view">EL: operator</a></li>
				<li>error page
					<ul>
						<li><a href="/page/uncategorized/response-bad-request-test.view">400 error page</a></li>
						<li><a href="/page/uncategorized/response-forbidden-test.view">403 error page</a></li>
						<li><a href="/some-not-exist-url">404 error page</a></li>
						<li><a href="/page/uncategorized/response-not-found-error-test.view">404 error page 2</a></li>
						<li><a href="/page/uncategorized/make-some-error-test.view">500 error page</a></li>
					</ul>
				</li>
				<li>servlet filter test
					<ul>
						<li><a href="/page/filter/replace-httpservletrequest-test.view">HttpServletRequest 구현부 교체</a></li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>