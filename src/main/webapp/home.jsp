<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.User" %>
<%@ include file="../includes/navbar.jsp" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>

<ul>
    <li><a href="logado?command=ListLinks">Meus Links Curtos</a></li>
    <li><a href="logado?command=ViewStats">Estatísticas</a></li>
    <li><a href="logado?command=Logout">Sair</a></li>
</ul>
