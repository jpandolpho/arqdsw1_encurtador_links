<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
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
        .link-container {
            margin-top: 80px;
        }
        hr {
            width: 80%;
            margin: 20px auto;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 50%;
            text-align: center;
        }
        h1 a {
            color: #007bff;
            text-decoration: none;
        }
        form {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }
        input[type="text"] {
            padding: 10px;
            width: 70%;
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
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<jsp:include page="/includes/navbar.jsp"/>
	<div class = "link-container">
	<%
		String link = (String) request.getAttribute("link");
		String msg = (String) request.getAttribute("msg");
		if (link != null ) {%>
			<h1><a target="_blank" href="<%="http://localhost:8080/arqdsw1_encurtador_links/curto/"+link %>">curto/<%=link %><img src="images/open_in_new.png"></a></h1>
	<%}else if(msg != null){%>
			<h1><%=msg %></h1>
	<%}%>	
	</div>
	<hr>
	<div class = "container">
	<form action="front.do?action=encurtar" method="post">
		<input type="text" name="textLink"
			placeholder="Digite o link" required="required">
			<br>
		<button type="submit">Encurtar</button>
	</form>
	</div>
</body>
</html>