<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
    <hr>

    <h2>Criar Novo Link Curto</h2>
    <form action="logado?command=EncurtarLink" method="post">
        <input type="text" name="textLink" placeholder="Digite o link" required="required">
        <button type="submit">Encurtar</button>
    </form>


    <%
        String link = (String) request.getAttribute("link");
        String msg = (String) request.getAttribute("msg");
        if (link != null) { 
    %>
        <h3>Link Curto Criado:</h3>
        <p><a target="_blank" href="<%= "http://localhost:8080/arqdsw1_encurtador_links/curto/" + link %>">curto/<%= link %></a></p>
    <%
        } else if (msg != null) {
    %>
        <h3><%= msg %></h3>
    <%
        }
    %>

    <ul>
        <li><a href="logado?command=ListLinks">Meus Links Curtos</a></li>
        <li><a href="logado?command=ViewStats">Estatísticas</a></li>
        <li><a href="logado?command=Logout">Sair</a></li>
    </ul>
</body>
</html>