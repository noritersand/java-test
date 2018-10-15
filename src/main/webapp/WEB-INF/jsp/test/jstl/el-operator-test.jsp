<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="/static/js/jquery-1.12.4.js"></script>
<title>scope test</title>
</head>
<body>
<h2>expression language operator test</h2>
<h3>empty:</h3>
<ul>
	<li>${longZero}, empty?: ${empty longZero}</li>
	<li>${longOne}, empty?: ${empty longOne}</li>
	<li>${longHundred}, empty?: ${empty longHundred}</li>
	<li>${mustBeNull ? 'is not null' : 'is null'}, empty?: ${empty mustBeNull}</li>
	<li>${map}, empty?: ${empty map.longThousand}, empty2?: ${empty map.a.b.c.d.f.e.d}</li>
</ul>
</body>
</html>
