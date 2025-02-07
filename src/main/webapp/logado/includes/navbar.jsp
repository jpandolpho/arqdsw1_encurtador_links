<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav>
	<a href="<%=request.getContextPath()%>/logado.do?command=Home">Home</a>
	<a href="<%=request.getContextPath()%>/logado.do?command=ListLinks">Meus links</a>
	<a href="<%=request.getContextPath()%>/logado.do?command=Logout">Logout</a>
</nav>