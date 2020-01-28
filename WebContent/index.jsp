<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Curso JSP</title>
<link rel="stylesheet" href="resources/css/estilos.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<h2 class="msg">${msg }</h2>
			<form class="login-form" action="LoginServlet" method="post">
				<input type="text" id="login" name="login" placeholder="login" /> 
				<input type="password" id="senha" name="senha" placeholder="senha" />
				<button type="submit">Entrar</button>
			</form>
		</div>
	</div>
</body>
</html>