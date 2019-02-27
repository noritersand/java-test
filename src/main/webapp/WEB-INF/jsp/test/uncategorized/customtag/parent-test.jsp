<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>parent</title>
</head>
<body>
<h2>HEL로 world드</h2>

<c:set var="attr2" value="56778" scope="request" />

<tags:example tagattr="one" />
<tags:example tagattr="two" />
</body>
</html>
