<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Link" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Access" %>
<jsp:include page="includes/navbar.jsp" />
<head>
<style>
 		nav {
	        background-color: #333;
	        padding: 15px;
	        width: 100%;
	        text-align: center;
	        display: flex;
	        justify-content: center;
	        gap: 20px;
	        position: fixed;
	        top: 0;
	        left: 0;
	        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
	    }
	    .navbar-container {
	        display: flex;
	        gap: 15px;
	    }
	    nav a {
	        color: white;
	        text-decoration: none;
	        font-size: 18px;
	        padding: 8px 16px;
	        border-radius: 5px;
	        transition: background 0.3s ease;
	    }
	    nav a:hover {
	        background: #555;
	    }
	    nav a:visited {
    		color: white; 
		}
		nav a:focus,
		nav a:active {
		    outline: none; 
		    color: white;
		}
    .access-table {
        margin-top: 20px;
        border-collapse: collapse;
        width: 100%;
    }
    .access-table th, .access-table td {
        border: 1px solid #ddd;
        padding: 8px;
    }
    .access-table th {
        background-color: #f2f2f2;
        text-align: left;
    }
    .access-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .access-table tr:hover {
        background-color: #f1f1f1;
    }
    .modal-body {
        max-height: 400px;
        overflow-y: auto;
    }
    .hidden {
        display: none;
    }
</style>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<%
    List<Link> links = (List<Link>) request.getAttribute("links");
%>

<div class="container mt-4">
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
                        String modalEditId = "editModal_" + link.getLinkEncurtado();
                        String accessTableId = "accessTable_" + link.getLinkEncurtado();
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
                                <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#<%= modalEditId %>">Editar</button>
                                <button type="button" class="btn btn-info btn-sm" onclick="toggleAccessTable('<%= accessTableId %>')">Ver Acessos</button>
                            </td>
                        </tr>
                        
                        <tr id="<%= accessTableId %>" class="hidden">
                            <td colspan="3">
                                <table class="access-table">
                                    <thead>
                                        <tr>
                                            <th>Link Longo</th>
                                            <th>Link Curto</th>
                                            <th>Endereço IP</th>
                                            <th>Contagem de Acessos</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            if (!link.getAcessos().isEmpty()) {
                                                for (Access access : link.getAcessos().keySet()) {
                                        %>
                                                    <tr>
                                                        <td><%= link.getLinkOriginal() %></td>
                                                        <td><%= link.getLinkEncurtado() %></td>
                                                        <td><%= access.getIpAddress() %></td>
                                                        <td><%= link.getAcessos().get(access) %></td>
                                                    </tr>
                                        <%
                                                }
                                            } else {
                                        %>
                                                <tr>
                                                    <td colspan="4" class="text-center">Nenhum acesso registrado para este link.</td>
                                                </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </td>
                        </tr>

                        <div class="modal fade" id="<%= modalEditId %>" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Editar Link</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="LogadoServlet" method="post">
                                            <input type="hidden" name="command" value="EditLink">
                                            <input type="hidden" name="curto" value="<%= link.getLinkEncurtado() %>">
                                            <div class="mb-3">
                                                <label class="form-label">Novo Link Curto</label>
                                                <input type="text" class="form-control" name="novoCurto" value="<%= link.getLinkEncurtado() %>" required>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Novo Link Original</label>
                                                <input type="text" class="form-control" name="novoOriginal" value="<%= link.getLinkOriginal() %>" required>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Salvar</button>
                                        </form>
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

<script>
    function toggleAccessTable(tableId) {
        var table = document.getElementById(tableId);
        if (table.classList.contains("hidden")) {
            table.classList.remove("hidden");
        } else {
            table.classList.add("hidden");
        }
    }
</script>