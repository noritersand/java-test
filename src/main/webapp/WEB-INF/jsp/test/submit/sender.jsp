<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sender UTF-8</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/test/submit/receiver.view" accept-charset="UTF-8">
	<fieldset>
		<legend>GET method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<form method="post" action="/test/submit/receiver.view" accept-charset="UTF-8">
	<fieldset>
		<legend>POST method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<br><a href="/test/submit/sender-iso-8859-1.view">ISO-8859-1로 보내기</a>
<br><a href="/test/submit/sender-euc-kr.view">EUC-KR로 보내기</a>
</body>
</html>
