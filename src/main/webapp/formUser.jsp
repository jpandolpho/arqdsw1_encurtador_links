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
    z-index: 1000;
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
    margin-top: 100px;
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
    margin-top: 120px; /* Para não ficar oculto pela navbar */
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
    gap: 15px;
}

input[type="text"],
input[type="password"] {
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
	<div class = "container">
	<% boolean login = (Boolean) request.getAttribute("login"); %>
	<jsp:include page="/includes/navbar.jsp"/>
	<%
		String msg = (String) request.getAttribute("msg");
		if (msg != null ) {%>
			<h1><%=msg %></h1>
	<%}%>
	<hr>
	<%if(!login){ %>
	<form action="front.do?action=saveUser" method="post">
		<label for="login">Usuário</label> 
		<input type="text" id="login" name="textLogin"
			placeholder="Digite o nome de usuário que deseja." required="required">
		
		<label for="password">Senha</label> 
		<input type="password" id="password" name="textPassword"
			placeholder="Digite a senha segura." required="required">
		
		<button type="submit">Cadastrar</button>
	<%}else{ %>
	<form action="front.do?action=login" method="post">
		<label for="login">Usuário</label> 
		<input type="text" id="login" name="textLogin"
			placeholder="Digite o nome de usuário." required="required">
		
		<label for="password">Senha</label> 
		<input type="password" id="password" name="textPassword"
			placeholder="Digite a senha." required="required">
		
		<button type="submit">Login</button>
	<%} %>
	</form>
	</div>
</body>
</html>