<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="euc-kr">
<title>sender EUC-KR</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/test/uncategorized/submission-charset/receiver-test.view" accept-charset="euc-kr">
	<fieldset>
		<legend>GET method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<form method="post" action="/test/uncategorized/submission-charset/receiver-test.view" accept-charset="euc-kr">
	<fieldset>
		<legend>POST method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<br><a href="/test/uncategorized/submission-charset/sender-test.view">UTF-8로 보내기</a>
<br><a href="/test/uncategorized/submission-charset/sender-iso-8859-1-test.view">ISO-8859-1로 보내기</a>
<br><a href="/html-test/submission-charset-test/without-content-type-header.html">Content-Type 없을때의 글자 인코딩 테스트</a>
</body>
</html>
