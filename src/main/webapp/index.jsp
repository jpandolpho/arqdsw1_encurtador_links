<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
	<jsp:include page="/includes/navbar.jsp"/>
	<hr>
	<%
		String link = (String) request.getAttribute("link");
		if (link != null ) {%>
			<a href="<%="http://localhost:8080/arqdsw1_encurtador_links/curto/"+link %>">link</a>
		<%}%>
	<form action="front.do?action=encurtar" method="post">
		<input type="text" name="textLink"
			placeholder="Digite o link" required="required">
		<button type="submit">Encurtar</button>
	</form>
</body>
</html>