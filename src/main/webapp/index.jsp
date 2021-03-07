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
<!-- 		<li> -->
<!-- 			<h4>JavaScript</h4> -->
<!-- 			<ul> -->
<!-- 				<li><a href="/page/js/cookie/cookie-util-test.html">cookie: cookie util</a></li> -->
<!-- 				<li><a href="/page/js/parameter/parameter-util-test.html">parameter: parameter util</a></li> -->
<!-- 				<li>AJAX -->
<!-- 					<ul> -->
<!-- 						<li><a href="/page/js/ajax/ajax-test.html">JavaScript AJAX</a></li> -->
<!-- 						<li><a href="/page/js/ajax/cors-test.html">CORS test</a></li> -->
<!-- 						<li><a href="/page/js/ajax/return-value-with-ajax-test.html">비동기 통신의 반환값은 어떻게 처리될까</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 				<li>date -->
<!-- 					<ul> -->
<!-- 						<li><a href="/page/js/date/select-month-test.html">date: select month 셀렉트 박스에서 달 선택</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 				<li>form -->
<!-- 					<ul> -->
<!-- 						<li><a href="/page/js/form/form-to-json-test.html">form: Form element to JSON</a></li> -->
<!-- 					</ul> -->
<!-- 				</li> -->
<!-- 				<li>event -->
<!-- 					 <ul> -->
<!-- 					 	<li><a href="/page/js/event/keyboard-event-keycode-test.html">KeyboardEvent.keyCode</a></li> -->
<!-- 					 </ul> -->
<!-- 				</li>				 -->
<!-- 			</ul>	 -->
<!-- 		</li> -->
<!-- 		<li> -->
<!-- 			<h4>jQuery</h4> -->
<!-- 			<ul> -->
<!-- 				<li><a href="/page/jquery/exercise-book-jquery-1-x-x.html">[연습장] jQuery 1.x</a></li> -->
<!-- 				<li><a href="/page/jquery/ajax-test.html">ajax</a></li> -->
<!-- 				<li><a href="/page/jquery/ajax-setup-test.html">ajaxSetup</a></li> -->
<!-- 				<li><a href="/page/jquery/append-input-tag-from-querystring-test.html">querystring to input tag</a></li> -->
<!-- 				<li><a href="/page/jquery/exercise-book-jquery-3-x-x.html">[연습장] jQuery 3.x</a></li> -->
<!-- 				<li><a href="/page/jquery/jquery-3-x-x-test.html">jQuery 3.x</a></li> -->
<!-- 				<li><a href="/page/jquery/form-empty-check-test.html">form empty check</a></li> -->
<!-- 				<li><a href="/page/jquery/set-value-in-attribute-test.html">set value in attribute</a></li> -->
<!-- 				<li><a href="/page/jquery/cors-test.html">CORS</a></li> -->
<!-- 				<li><a href="/page/jquery/selector-test.html">selector</a></li> -->
<!-- 				<li><a href="/page/jquery/form-value-save-and-load-test.html">form value save and load</a></li> -->
<!-- 			</ul> -->
<!-- 		</li> -->
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