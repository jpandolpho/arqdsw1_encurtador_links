<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
	<jsp:include page="/includes/navbar.jsp"/>
	<hr>
	<form action="front.do?action=encurtar" method="post">
		<input type="text" name="textLink"
			placeholder="Digite o link" required="required">
		<button type="submit">Encurtar</button>
	</form>
</body>
</html>