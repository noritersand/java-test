<%@ page language="java" contentType="text/html; charset=euc-kr" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="euc-kr">
<title>sender EUC-KR</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/test/submit/receiver.view" accept-charset="euc-kr">
	<fieldset>
		<legend>GET method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<form method="post" action="/test/submit/receiver.view" accept-charset="euc-kr">
	<fieldset>
		<legend>POST method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<br><a href="/test/submit/sender.view">UTF-8로 보내기</a>
<br><a href="/test/submit/sender-iso-8859-1.view">ISO-8859-1로 보내기</a>
</body>
</html>
