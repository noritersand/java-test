<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" type="image/png" href="/static/image/favicon/favicon.png">
<link rel="shortcut icon" href="/static/image/favicon/favicon.ico">
<title>index</title>
</head>
<body>
<h1>LABORATORY</h1>
<h2>Index</h2>
<ul>
	<li>
		<h4>HTTP</h4>
		<ul>
			<li><a href="/http-test/response-status-code.view">HTTP 응답 코드 테스트</a></li>
		</ul>
	</li>
	<li>
		<h4>Browser</h4>
		<ul>
			<li><a href="/browser-test/new-window.html">new window 테스트</a></li>
			<li><a href="/browser-test/new-window-popup.html">new window(팝업) 테스트</a></li>
		</ul>
	</li>
	<li>
		<h4>Security</h4>
		<ul>
			<li><a href="/security-test/xss-test.view?param1=">XSS 테스트</a></li>
		</ul>
	</li>
	<li>
		<h4>HTML &amp; CSS</h4>
		<ul>
			<li><a href="/html-test/tag/tooltip.html">툴팁</a></li>
			<li><a href="/html-test/tag/fieldset.html">&lt;fieldset&gt;</a></li>
			<li><a href="/html-test/tag/a.html">&lt;a&gt;</a></li>
			<li><a href="/html-test/tag/label.html">&lt;label&gt;</a></li>
			<li><a href="/html-test/css/div-align.html">div align</a></li>
			<li><a href="/html-test/opener/opener-test.html">opener test</a></li>
			<li><a href="/html-test/opener/opener-test3.html">opener test #2</a></li>
			<li><a href="/html-test/iframe/iframe-test1.html">iframe test</a></li>
			<li><a href="/html-test/relative-path/relative-path-test1.html">relative path test</a></li>
			<li><a href="/html-test/submission-charset-test/without-content-type-header.html">Content-Type 없을때의 글자 인코딩 테스트</a></li>
			<li><a href="/html-test/event/input-date.html">input-date event test</a></li>
		</ul>
	</li>
	<li>
		<h4>JavaScript</h4>
		<ul>
			<li>array
				<ul>
					<li><a href="/js-test/array/splice-test.html">array: splice test: 배열의 특정 요소 삭제</a></li>
					<li><a href="/js-test/array/array-splice.html">array: splice</a></li>
					<li><a href="/js-test/array/array-splice2.html">array: splice #2</a></li>
				</ul>
			</li>
			<li><a href="/js-test/cookie/cookie-util.html">cookie: cookie util</a></li>
			<li>date
				<ul>
					<li><a href="/js-test/date/date.html">date test</a></li>
					<li><a href="/js-test/date/calculate-elapsed-time.html">date: calculate elapsed time</a></li>
					<li><a href="/js-test/date/add-months.html">date: add months 자바스크립트 달 넘기기</a></li>
					<li><a href="/js-test/date/add-months2.html">date: add months 자바스크립트 달 넘기기2</a></li>
					<li><a href="/js-test/date/select-month.html">date: select month 셀렉트 박스에서 달 선택</a></li>
					<li><a href="/js-test/date/new-date.html">date: new date</a></li>
				</ul>
			</li>
			<li><a href="/js-test/history/history-mother.html">history of iframe</a></li>
			<li>number-format
				<ul>
					<li><a href="/js-test/number-format/extended.html">number-format: prototype extend</a></li>
					<li><a href="/js-test/number-format/indecipherable.html">number-format: indecipherable</a></li>
					<li><a href="/js-test/number-format/short.html">number-format: short</a></li>
					<li><a href="/js-test/number-format/super-extended.html">number-format: super extended</a></li>
				</ul>
			</li>
			<li>plain-object
				<ul>
					<li><a href="/js-test/plain-object/array-search-match-object.html">plain-object: array search match object</a></li>
					<li><a href="/js-test/plain-object/delete-duplicate-properties.html">plain-object: delete duplicate properties</a></li>
					<li><a href="/js-test/plain-object/delete-not-specified-property.html">plain-object: delete not specified property</a></li>
					<li><a href="/js-test/plain-object/convert-querystring-to-json.html">plain-object: convert querystring to JSON</a></li>
					<li><a href="/js-test/plain-object/object-array-to-array-object.html">plain-object: object-array to array-object</a></li>
				</ul>
			</li>
			<li>statement
				<ul>
					<li><a href="/js-test/statement/console-time-test.js">statement: console time test</a></li>
					<li><a href="/js-test/statement/for-each-test.html">statement: for each test</a></li>
					<li><a href="/js-test/statement/for-in-test.html">statement: for in test</a></li>
					<li><a href="/js-test/statement/for-of-test.html">statement: for of test</a></li>
					<li><a href="/js-test/statement/let-test.html">statement: let test</a></li>
					<li><a href="/js-test/statement/reference-test.html">statement: reference test</a></li>
				</ul>
			</li>
			<li>string
				<ul>
					<li><a href="/js-test/string/index-of-test.html">string: indexOf test</a></li>
					<li><a href="/js-test/string/split-join-test.html">string: split and join</a></li>
					<li><a href="/js-test/string/calculate-byte-length.html">string: calculate byte length</a></li>
				</ul>
			</li>
			<li><a href="/js-test/phone-number/lib-phone-number-test.html">phone-number: library test</a></li>
			<li>AJAX
				<ul>
					<li><a href="/js-test/ajax/cors-test.html">JavaScript CORS test</a></li>
				</ul>
			</li>
		</ul>	
	</li>
	<li>
		<h4>jQuery</h4>
		<ul>
			<li><a href="/jquery-test/ajax.html">ajax test</a></li>
			<li><a href="/jquery-test/ajaxSetup.html">ajaxSetup</a></li>
			<li><a href="/jquery-test/append-input-tag-from-querystring.html">querystring to input tag</a></li>
			<li><a href="/jquery-test/jquery-3-x-x-test.html">jquery 3.x test</a></li>
			<li><a href="/jquery-test/form-empty-check.html">form empty check</a></li>
			<li><a href="/jquery-test/set-value-in-attribute.html">set value in attribute</a></li>
			<li><a href="/jquery-test/cors-test.html">jQuery CORS test</a></li>
		</ul>
	</li>
	<li>
		<h4>JAVA &amp; JSP &amp; Servlet</h4>
		<ul>
			<li><a href="/test/reponse-character-set-test.data">응답 문자셋 테스트</a></li>
			<li><a href="/test/submission-charset-test/sender.view">유니코드 문자 서브미션 테스트</a></li>
			<li>include
				<ul>
					<li><a href="/test/include-test/with-jsp.view">include test with jsp</a></li>
					<li><a href="/test/include-test/with-html.view">include test with html</a></li>
				</ul>
			</li>
			<li>파일 업로드
				<ul>
					<li><a href="/test/fileupload/with-oreilly.view">oreilly multipart</a></li>
				</ul>
			</li>
			<li><a href="/test/what-is-payload-body.view">payload body란 무엇인가?</a></li>
			<li><a href="/test/session-invalidate-test.view">session invalidate test</a></li>
			<li><a href="/test/customtag/parent.view">custom tag</a></li>
			<li><a href="/test/jstl/parseNumberFromString.view">parse number from string</a></li>
			<li><a href="/test/jstl/jstl-test.view">JSTL test</a></li>
			<li><a href="/test/jstl/el-scope-test.view">EL: scope test</a></li>
			<li><a href="/test/jstl/el-operator-test.view">EL: operator test</a></li>
			<li>error page
				<ul>
					<li><a href="/some-not-exits-url">404 error page</a></li>
					<li><a href="/test/response-not-found-error.view">404 error page 2</a></li>
					<li><a href="/test/make-some-error.view">500 error page</a></li>
				</ul>
			</li>
			<li>servlet filter test
				<ul>
					<li><a href="/filter-test/replace-httpservletrequest.view">HttpServletRequest 구현부 교체 테스트</a></li>
				</ul>
			</li>
		</ul>
	</li>
</ul>
</body>
</html>