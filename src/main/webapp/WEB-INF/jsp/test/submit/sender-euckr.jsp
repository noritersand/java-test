<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>sender EUC-KR</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/test/submit/receiver.view" accept-charset="EUC-KR">
	<fieldset>
		<legend>GET method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<form method="post" action="/test/submit/receiver.view" accept-charset="EUC-KR">
	<fieldset>
		<legend>POST method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<br><a href="/test/submit/sender.view">UTF-8로 보내기</a>
<br><a href="/test/submit/sender-iso.view">ISO-8859-1로 보내기</a>
</body>
</html>