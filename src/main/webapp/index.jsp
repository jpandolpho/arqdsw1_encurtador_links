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
		String msg = (String) request.getAttribute("msg");
		if (link != null ) {%>
			<h1><a target="_blank" href="<%="http://localhost:8080/arqdsw1_encurtador_links/curto/"+link %>">curto/<%=link %><img src="images/open_in_new.png"></a></h1>
	<%}else if(msg != null){%>
			<h1><%=msg %></h1>
	<%}%>
	<form action="front.do?action=encurtar" method="post">
		<input type="text" name="textLink"
			placeholder="Digite o link" required="required">
		<button type="submit">Encurtar</button>
	</form>
</body>
</html>