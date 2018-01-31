<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/resources/js/jquery-1.12.4.js"></script>
<title>scope test</title>
<style type="text/css">
table {
	border-collapse: collapse;
}
th, tr, td {
	border: 1px solid black;
	padding: 5px 5px 5px 5px;
}
</style>
</head>
<body>
<h2>expression language test</h2>
<%!
	final String globalVar = "i'm text";
%>
<%
	final String localVar = "i'm text2";
	HashMap<String, String> obj = new HashMap<>();
%>

<h3>1. get java variable</h3>
<table>
	<tr>
		<th>scriptlet: </th>
		<td><%=globalVar%></td>
		<td><%=localVar%></td>
	</tr>
	<tr>
		<th>JSTL</th>
		<td><c:out value="${globalVar}" default="안나옴" /></td>
		<td><c:out value="localVar" default="안나옴" /></td>
	</tr>
	<tr>
		<th>EL</th>
		<td>${empty globalVar ? '안나옴' : globalVar}</td>
		<td>${empty localVar ? '안나옴' : localVar}</td>
	</tr>
</table>

<h3>2. get java static variable</h3>
<table>
	<tr>
		<th>scriptlet: </th>
		<td><%=java.lang.Math.PI%></td>
	</tr>
	<tr>
		<th>JSTL</th>
		<td><c:out value="${java.lang.Math.PI}" default="안나옴" /></td>
	</tr>
	<tr>
		<th>EL</th>
		<td>${empty java.lang.Math.PI ? '안나옴' : java.lang.Math.PI}</td>
	</tr>
</table>

<h3>3. JSTL: core-set #1</h3>
<c:set target="<%=obj%>" property="mynameis" value="waldo" />
<table>
	<tr>
		<th>scriptlet: </th>
		<td><%=obj.get("mynameis") %></td>
	</tr>
	<tr>
		<th>JSTL</th>
		<td><c:out value="<%=obj%>" /></td>
	</tr>
	<tr>
		<th>EL</th>
		<td>${empty obj.mynameis ? '안나옴' : obj.mynameis}</td>
	</tr>
</table>

<h3>4. JSTL: core-set #2</h3>
<c:set var="attr" value="hi" /> <%-- request.setAttribute("attr", "hi") --%>
<table>
	<tr>
		<th>scriptlet: </th>
		<td><%=pageContext.getAttribute("attr") %></td>
	</tr>
	<tr>
		<th>JSTL</th>
		<td><c:out value="${attr}" /></td>
	</tr>
	<tr>
		<th>EL</th>
		<td>${attr}</td>
	</tr>
</table>
</body>
</html>
