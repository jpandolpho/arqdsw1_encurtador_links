<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Link" %>
<%@ page import="java.util.List" %>
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

    <h2>Meus Links Curtos</h2>
    
    <%
        List<Link> userLinks = (List<Link>) request.getAttribute("userLinks");
        if (userLinks == null || userLinks.isEmpty()) {
    %>
        <p>Você ainda não tem links encurtados.</p>
    <%
        } else {
    %>
        <ul>
            <%
                for (Link link : userLinks) {
            %>
                <li><a href="<%= "http://localhost:8080/arqdsw1_encurtador_links/curto/" + link.getLinkEncurtado() %>" target="_blank">curto/<%= link.getLinkEncurtado() %></a></li>
            <%
                }
            %>
        </ul>
    <%
        }
    %>

    <ul>
        <li><a href="logado?command=Home">Voltar para a página inicial</a></li>
        <li><a href="logado?command=Logout">Sair</a></li>
    </ul>
</body>
</html>
