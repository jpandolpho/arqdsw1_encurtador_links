<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Link" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.User" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Access" %>
<jsp:include page="includes/navbar.jsp" />

<%
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
                                <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editModal<%= link.getLinkEncurtado() %>">Editar</button>
                                <button type="button" class="btn btn-info btn-sm" 
                                        onclick="window.location.href='LogadoServlet?command=ShowAccess&curto=<%= link.getLinkEncurtado() %>'">
                                    ...
                                </button>
                            </td>
                        </tr>
                        
                        <!-- Modal Edição -->
                        <div class="modal fade" id="editModal<%= link.getLinkEncurtado() %>" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editModalLabel">Editar Link</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="LogadoServlet" method="post">
                                            <input type="hidden" name="command" value="EditLink">
                                            <input type="hidden" name="curto" value="<%= link.getLinkEncurtado() %>">
                                            <div class="mb-3">
                                                <label for="novoCurto" class="form-label">Novo Link Curto</label>
                                                <input type="text" class="form-control" id="novoCurto" name="novoCurto" value="<%= link.getLinkEncurtado() %>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="novoOriginal" class="form-label">Novo Link Original</label>
                                                <input type="text" class="form-control" id="novoOriginal" name="novoOriginal" value="<%= link.getLinkOriginal() %>" required>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Salvar</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                         <!-- Modal de Acessos -->
                        <div class="modal fade" id="accessModal<%= link.getLinkEncurtado() %>" tabindex="-1" aria-labelledby="accessModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="accessModalLabel">Acessos do Link</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <%
                                            if (!link.getAcessos().isEmpty()) {
                                        %>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th>Endereço IP</th>
                                                            <th>Contagem de Acessos</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            for (Access access : link.getAcessos().keySet()) {
                                                        %>
                                                                <tr>
                                                                    <td><%= access.getIpAddress() %></td>
                                                                    <td><%= link.getAcessos().get(access) %></td>
                                                                </tr>
                                                        <%
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                        <%
                                            } else {
                                        %>
                                                <p>Nenhum acesso registrado para este link.</p>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
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
