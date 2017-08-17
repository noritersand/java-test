<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include test</title>
</head>
<body>
<h2>include with JSP</h2>

<h3>include by JSP directive</h3>
<%@include file="/WEB-INF/jsp/test/include-test/include-me.jsp" %>

<h3>include by JSP action tag</h3>
<jsp:include page="/WEB-INF/jsp/test/include-test/include-me.jsp" />

<h3>include by weird way</h3>
<c:set var="fileLocation" value="/WEB-INF/jsp/test/include-test/include-me.jsp" scope="request" />
<jsp:include page="${fileLocation}" />

</body>
</html>