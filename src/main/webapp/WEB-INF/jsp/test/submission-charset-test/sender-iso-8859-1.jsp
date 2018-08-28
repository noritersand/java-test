<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="iso-8859-1">
<title>sender ISO-8859-1</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/test/submission-charset-test/receiver.view" accept-charset="iso-8859-1">
	<fieldset>
		<legend>GET method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<form method="post" action="/test/submission-charset-test/receiver.view" accept-charset="iso-8859-1">
	<fieldset>
		<legend>POST method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<br><a href="/test/submission-charset-test/sender.view">UTF-8로 보내기</a>
<br><a href="/test/submission-charset-test/sender-euc-kr.view">EUC-KR로 보내기</a>
<br><a href="/html-test/submission-charset-test/without-content-type-header.html">Content-Type 없을때의 글자 인코딩 테스트</a>
</body>
</html>
