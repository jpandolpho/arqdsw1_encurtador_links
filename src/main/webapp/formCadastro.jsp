<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/includes/head.html"/>
<body>
	<jsp:include page="/includes/navbar.jsp"/>
	<hr>
	<form action="front.do?action=saveUser" method="post">
		<label for="login">E-mail</label> 
		<input type="text" id="login" name="textLogin"
			placeholder="Digite o nome de usuário que deseja." required="required">
		
		<label for="password">Senha</label> 
		<input type="password" id="password" name="textPassword"
			placeholder="Digite a senha segura." required="required">
		
		<button type="submit">Cadastrar</button>
	</form>
</body>
</html>