<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>parse number from string</title>
</head>
<body>
<h1>parse number from string</h1>
<c:set var="first" value="2017" />
<c:set var="second" value="2018" />
first: ${first}, second: ${second}
<fmt:parseNumber var="firstNumber" value="${first}" />
<fmt:parseNumber var="secondNumber" value="${second}" /><br>
firstNumber &gt; secondNumber: ${firstNumber gt secondNumber}<br>
firstNumber &lt; secondNumber: ${firstNumber lt secondNumber}<br>
</body>
</html>