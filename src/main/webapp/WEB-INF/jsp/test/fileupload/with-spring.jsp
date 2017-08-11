<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file upload - spring multipart</title>
</head>
<body>
<h2>file upload - spring multipart</h2>
<form method="post" action="/test/fileupload/upload-with-spring.data" enctype="multipart/form-data">
	<input type="text" name="title" placeholder="파일제목" /><br>
	<input type="file" name="uploadfile" /><br>
	<input type="file" name="uploadfile2" /><br>
	<input type="file" name="uploadfile3" /><br>
	<input type="file" name="uploadfile4" /><br>
	<input type="file" name="uploadfile5" /><br>
	<input type="submit" value="업로드" /> 
</form>
</body>
</html>
