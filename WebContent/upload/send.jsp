<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function regist(){
	form1.action="/device/upload";
	
	form1.encoding="multipart/form-data";
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>

	<form name="form1">
		<input type="text" name="title">
		<input type="file" name="myFile">
		<input type="button" value="파일전송" onClick="regist()">
	</form>
	
</body>
</html>











