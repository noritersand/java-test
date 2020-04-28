<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>index</title>
<meta charset="utf-8">
<link rel="icon" type="image/png" href="/static/image/favicon/favicon.png">
<link rel="shortcut icon" href="/favicon.ico">
<link rel="stylesheet" href="/static/css/style.css"/>
</head>
<body>
<div class="align-center">
	<h1>LABORATORY</h1>
	<h2>Index</h2>
	<ul>
		<li><a href="/page/html/test-file-template.html">테스트 파일 템플릿</a></li>
		<li>
			<h4>HTTP</h4>
			<ul>
				<li><a href="/page/http/response-status-code-test.view">HTTP 응답 코드</a></li>
			</ul>
		</li>
		<li>
			<h4>Browser</h4>
			<ul>
				<li><a href="/page/browser/new-window-test.html">new window</a></li>
				<li><a href="/page/browser/popup/new-window-popup-test.html">new window(팝업)</a></li>
				<li><a href="/page/browser/history/browser-history-test.html">browser history</a></li>
				<li><a href="/page/browser/history/iframe/iframe-history-test-mother.html">history of iframe</a></li>
			</ul>
		</li>
		<li>
			<h4>Security</h4>
			<ul>
				<li><a href="/page/security/xss-test.view?param1=">XSS</a></li>
			</ul>
		</li>
		<li>
			<h4>HTML</h4>
			<ul>
				<li><a href="/page/html/tag/html5-input-types-test.html">HTML5 Input Types</a></li>
				<li><a href="/page/html/tag/tooltip-test.html">툴팁</a></li>
				<li><a href="/page/html/tag/fieldset-test.html">&lt;fieldset&gt;</a></li>
				<li><a href="/page/html/tag/a-test.html">&lt;a&gt;</a></li>
				<li><a href="/page/html/tag/label-test.html">&lt;label&gt;</a></li>
				<li><a href="/page/html/tag/video-test.html">&lt;video&gt;</a></li>
				<li><a href="/page/html/tag/select-test.html">&lt;select&gt;</a></li>
				<li><a href="/page/html/opener/opener-test.html">opener</a></li>
				<li><a href="/page/html/opener/opener-test3.html">opener #2</a></li>
				<li><a href="/page/html/iframe/iframe-test1.html">iframe</a></li>
				<li><a href="/page/html/relative-path/relative-path-test1.html">relative path</a></li>
				<li><a href="/page/html/submission-charset/without-content-type-header.html">Content-Type 없을때의 글자 인코딩</a></li>
				<li><a href="/page/html/event/input-date-test.html">input-date event</a></li>
			</ul>
		</li>
		<li>
			<h4>CSS</h4>
			<ul>
				<li><a href="/page/html/css/align-center-1.html">가운데 정렬 #1</a></li>
				<li><a href="/page/html/css/align-center-2.html">가운데 정렬 #2</a></li>
				<li><a href="/page/html/css/align-center-3.html">가운데 정렬 + 검정색 배경 + 인라인 스타일</a></li>
				<li><a href="/page/html/css/how-draw-pretty-test.html">가운데 정렬 + 그라데이션 + 비디오</a></li>
			</ul>
		</li>
		<li>
			<h4>JavaScript</h4>
			<ul>
				<li><a href="/page/js/cookie/cookie-util-test.html">cookie: cookie util</a></li>
				<li><a href="/page/js/parameter/parameter-util-test.html">parameter: parameter util</a></li>
				<li>AJAX
					<ul>
						<li><a href="/page/js/ajax/ajax-test.html">JavaScript AJAX</a></li>
						<li><a href="/page/js/ajax/cors-test.html">CORS test</a></li>
						<li><a href="/page/js/ajax/return-value-with-ajax-test.html">비동기 통신의 반환값은 어떻게 처리될까</a></li>
					</ul>
				</li>
				<li>date
					<ul>
						<li><a href="/page/js/date/select-month-test.html">date: select month 셀렉트 박스에서 달 선택</a></li>
					</ul>
				</li>
				<li>form
					<ul>
						<li><a href="/page/js/form/form-to-json-test.html">form: Form element to JSON</a></li>
					</ul>
				</li>
				<li>event
					 <ul>
					 	<li><a href="/page/js/event/keyboard-event-keycode-test.html">KeyboardEvent.keyCode</a></li>
					 </ul>
				</li>				
			</ul>	
		</li>
		<li>
			<h4>jQuery</h4>
			<ul>
				<li><a href="/page/jquery/exercise-book-jquery-1-x-x.html">[연습장] jQuery 1.x</a></li>
				<li><a href="/page/jquery/ajax-test.html">ajax</a></li>
				<li><a href="/page/jquery/ajax-setup-test.html">ajaxSetup</a></li>
				<li><a href="/page/jquery/append-input-tag-from-querystring-test.html">querystring to input tag</a></li>
				<li><a href="/page/jquery/exercise-book-jquery-3-x-x.html">[연습장] jQuery 3.x</a></li>
				<li><a href="/page/jquery/jquery-3-x-x-test.html">jQuery 3.x</a></li>
				<li><a href="/page/jquery/form-empty-check-test.html">form empty check</a></li>
				<li><a href="/page/jquery/set-value-in-attribute-test.html">set value in attribute</a></li>
				<li><a href="/page/jquery/cors-test.html">CORS</a></li>
				<li><a href="/page/jquery/selector-test.html">selector</a></li>
				<li><a href="/page/jquery/form-value-save-and-load-test.html">form value save and load</a></li>
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