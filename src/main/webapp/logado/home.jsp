<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/includes/head.html"/>
<style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
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
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 50%;
            margin: 100px auto 20px;
            text-align: center;
        }
        h2 {
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }
        input[type="text"] {
            padding: 10px;
            width: 80%;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
            transition: background 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .message {
            margin-top: 20px;
            padding: 10px;
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
            border-radius: 5px;
            width: 80%;
            margin-left: auto;
            margin-right: auto;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
	<jsp:include page="./includes/navbar.jsp"/>
	<div class = "container">
	<h2>Criar Novo Link Curto</h2>
    <form action="logado.do?command=EncurtarLink" method="post">
        <input type="text" name="textLink" placeholder="Digite o link" required="required">
        <input type="text" name="textCurto" placeholder="Personalize seu link curto, se quiser" maxlength="12">
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
	</div>
</body>
</html>