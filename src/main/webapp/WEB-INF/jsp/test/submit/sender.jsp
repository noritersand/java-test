<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/test/submit/reciever.do">
	<fieldset>
		<legend>GET method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
<form method="post" action="/test/submit/reciever.do">
	<fieldset>
		<legend>POST method</legend>
		<input type="text" name="text" value="한글" />
		<input type="submit" value="submit" />
	</fieldset>
</form>
</body>
</html>