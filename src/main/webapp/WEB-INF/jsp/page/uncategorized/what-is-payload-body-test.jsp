<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>what is payload body</title>
    <meta charset="utf-8">
    <jsp:include page="/WEB-INF/jsp/include/head.jsp"/>
    <script src="/static/js/jquery-1.12.4.js"></script>
</head>
<body>
<div class="align-center">
    <h1>what the fork is payload body?</h1>
    <form action="/page/uncategorized/read-querystring.data" method="post">
        <fieldset>
            <legend>
                <del>im legend</del>
                sync request: form data
            </legend>
            <input type="text" name="myname" value="waldo"/>
            <input type="text" name="age" value="65536"/>
            <button type="submit">SUBMIT</button>
        </fieldset>
    </form>
    <form action="/page/uncategorized/read-querystring.data" method="post" enctype="application/x-www-form-urlencoded">
        <fieldset>
            <legend>sync request: form data #2</legend>
            <input type="text" name="myname" value="waldo"/>
            <input type="text" name="age" value="65536"/>
            <button type="submit">SUBMIT</button>
        </fieldset>
    </form>
    <form action="/page/uncategorized/read-querystring.data?1234" method="post"
          enctype="application/x-www-form-urlencoded">
        <fieldset>
            <legend>sync request: form data #3</legend>
            <button type="submit">SUBMIT</button>
        </fieldset>
    </form>
    <form action="/page/uncategorized/read-body.data" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>sync request: payload body</legend>
            <input type="text" name="myname" value="waldo"/>
            <button type="submit">SUBMIT</button>
        </fieldset>
    </form>
    <hr>
    <form>
        <fieldset>
            <legend>async request: form data</legend>
            <button type="button" onclick="send()">SEND</button>
        </fieldset>
    </form>
    <form>
        <fieldset>
            <legend>async request: form data #2</legend>
            <button type="button" onclick="send2()">SEND</button>
        </fieldset>
    </form>
    <form>
        <fieldset>
            <legend>async request: payload body</legend>
            <button type="button" onclick="send3()">SEND</button>
        </fieldset>
    </form>
    <form>
        <fieldset>
            <legend>async request: payload body #2</legend>
            <button type="button" onclick="send4()">SEND</button>
        </fieldset>
    </form>
    <script>
      function send() {
        $.ajax({
          url: '/page/uncategorized/read-querystring.data',
          method: 'post',
          data: 'a=1&b=2',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json', // response type
          success: function (response) {
            console.log(JSON.stringify(response));
          }
        });
      }

      function send2() {
        $.ajax({
          url: '/page/uncategorized/read-querystring.data',
          method: 'post',
          data: '{"a": 1, "b": 2}',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json', // response type
          success: function (response) {
            console.log(JSON.stringify(response));
          }
        });
      }

      function send3() {
        $.ajax({
          url: '/page/uncategorized/read-body.data',
          method: 'post',
          data: 'a=1&b=2',
          contentType: 'application/json',
          dataType: 'json', // response type
          success: function (response) {
            console.log(JSON.stringify(response));
          }
        });
      }

      function send4() {
        $.ajax({
          url: '/page/uncategorized/read-body.data',
          method: 'post',
          data: '{"a": 1, "b": 2}',
          contentType: 'application/json',
          dataType: 'json', // response type
          success: function (response) {
            console.log(JSON.stringify(response));
          }
        });
      }
    </script>
</div>
</body>
</html>
