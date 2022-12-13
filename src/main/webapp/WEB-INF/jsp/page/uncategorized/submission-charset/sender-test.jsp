<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>sender UTF-8</title>
</head>
<body>
<h1>input test</h1>
<form method="get" action="/page/uncategorized/submission-charset/receiver-test.view" accept-charset="utf-8">
    <fieldset>
        <legend>GET method</legend>
        <input type="text" name="text" value="한글"/>
        <input type="submit" value="submit"/>
    </fieldset>
</form>
<form method="post" action="/page/uncategorized/submission-charset/receiver-test.view" accept-charset="utf-8">
    <fieldset>
        <legend>POST method</legend>
        <input type="text" name="text" value="한글"/>
        <input type="submit" value="submit"/>
    </fieldset>
</form>
<br><a href="/page/uncategorized/submission-charset/sender-iso-8859-1-test.view">ISO-8859-1로 보내기</a>
<br><a href="/page/uncategorized/submission-charset/sender-euc-kr-test.view">EUC-KR로 보내기</a>
<br><a href="/page/html/submission-charset/without-content-type-header.html">Content-Type 없을때의 글자 인코딩 테스트</a>
</body>
</html>
