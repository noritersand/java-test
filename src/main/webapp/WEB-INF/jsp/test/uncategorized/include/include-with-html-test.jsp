<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>include test</title>
</head>
<body>
<h2>include with HTML</h2>

<h3>include by JSP directive</h3>
<%@include file="/WEB-INF/jsp/test/uncategorized/include/include-me.html" %>

<h3>include by JSP action tag</h3>
<jsp:include page="/WEB-INF/jsp/test/uncategorized/include/include-me.html" />

<h3>include by weird way</h3>
<c:set var="fileLocation" value="/WEB-INF/jsp/test/uncategorized/include/include-me.html" scope="request" />
<jsp:include page="${fileLocation}" />

</body>
</html>