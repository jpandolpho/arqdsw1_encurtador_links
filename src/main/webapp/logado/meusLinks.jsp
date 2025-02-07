<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Link" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.User" %>
<jsp:include page="includes/navbar.jsp" />

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Link> links = (List<Link>) request.getAttribute("links");
%>

<div class="container">
    <h2>Meus Links</h2>
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Original</th>
                <th>Encurtado</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (links != null && !links.isEmpty()) {
                    for (Link link : links) {
            %>
                        <tr>
                            <td><a href="<%= link.getLinkOriginal() %>" target="_blank"><%= link.getLinkOriginal() %></a></td>
                            <td><a href="curto/<%= link.getLinkEncurtado() %>" target="_blank"><%= request.getServerName() %>/curto/<%= link.getLinkEncurtado() %></a></td>
                             <td>
                                <form action="LogadoServlet" method="post" style="display:inline;" onsubmit="return confirm('Tem certeza que deseja excluir este link?');">
                                    <input type="hidden" name="command" value="DeleteLink">
                                    <input type="hidden" name="curto" value="<%= link.getLinkEncurtado() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                                </form>
                                <a href="LogadoServlet?command=EditLinkCommand&curto=<%= link.getLinkEncurtado() %>" class="btn btn-warning btn-sm">Editar</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="3" class="text-center">Nenhum link encontrado.</td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>
