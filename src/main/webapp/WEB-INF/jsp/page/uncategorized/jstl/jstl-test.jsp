<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="lab.constants.Const" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <script src="/static/js/jquery-1.12.4.js"></script>
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

<h3>1. get java variable</h3>
<%!
    final String globalVar = "i'm text";
%>
<%
    final String localVar = "i'm text2";
    HashMap<String, String> obj = new HashMap<>();
%>
<table>
    <tr>
        <th scope="col">scriptlet:</th>
        <td><%=globalVar%>
        </td>
        <td><%=localVar%>
        </td>
    </tr>
    <tr>
        <th scope="col">JSTL</th>
        <td><c:out value="${globalVar}" default="안나옴"/></td>
        <td><c:out value="localVar" default="안나옴"/></td>
    </tr>
    <tr>
        <th scope="col">EL</th>
        <td>${empty globalVar ? '안나옴' : globalVar}</td>
        <td>${empty localVar ? '안나옴' : localVar}</td>
    </tr>
</table>

<h3>2. get java static variable</h3>
<table>
    <tr>
        <th scope="col">scriptlet:</th>
        <td><%=java.lang.Math.PI%>
        </td>
        <td><%=Math.PI%>
        </td>
        <td><%=Integer.MAX_VALUE %>
        </td>
        <td><%=Const.NAME.name() %>
        </td>
    </tr>
    <tr>
        <th scope="col">JSTL</th>
        <td><c:out value="${java.lang.Math.PI}" default="패키지를 명시하면 안나옴"/></td>
        <td><c:out value="${Math.PI}" default="java.lang 패키지는 임포트 생략"/></td>
        <td><c:out value="${Integer.MAX_VALUE}"/></td>
        <td><c:out value="${Const.NAME.name()}"/></td>
    </tr>
    <tr>
        <th scope="col">EL</th>
        <td>${empty java.lang.Math.PI ? '패키지를 명시하면 안나옴' : java.lang.Math.PI}</td>
        <td>${empty Math.PI ? 'java.lang 패키지는 임포트 생략' : Math.PI}</td>
        <td>${Integer.MAX_VALUE}</td>
        <td>${Const.NAME.name()}</td>
    </tr>
</table>

<h3>3. JSTL-core: set tag #1</h3>
<c:set target="<%=obj%>" property="mynameis" value="waldo"/>
<table>
    <tr>
        <th scope="col">scriptlet:</th>
        <td><%=obj.get("mynameis") %>
        </td>
    </tr>
    <tr>
        <th scope="col">JSTL</th>
        <td><c:out value="<%=obj.get(\"mynameis\")%>"/></td>
    </tr>
    <tr>
        <th scope="col">EL</th>
        <td>${empty obj.mynameis ? '안나옴' : obj.mynameis}</td>
    </tr>
</table>

<h3>4. JSTL-core: set tag #2</h3>
<c:set var="attr" value="hello there!"/> <%-- request.setAttribute("attr", "hi") --%>
<c:set var="attr2" value="${attr}"/>
<table>
    <tr>
        <th scope="col">scriptlet:</th>
        <td><%=pageContext.getAttribute("attr") %>
        </td>
        <td><%=pageContext.getAttribute("attr2") %>
        </td>
    </tr>
    <tr>
        <th scope="col">JSTL</th>
        <td><c:out value="${attr}"/></td>
        <td><c:out value="${attr2}"/></td>
    </tr>
    <tr>
        <th scope="col">EL</th>
        <td>${attr}</td>
        <td>${attr2}</td>
    </tr>
</table>
</body>
</html>
