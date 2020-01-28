<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/estilos.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Lista de produtos</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Sua logo aqui</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Início</a></li>
					<li><a href="listarUsuario?acao=listarTodos">Usuários</a></li>
					<li><a href="listarProduto?acao=listarTodos">Produtos</a></li>
					<li><a href="index.jsp">Sair</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id="main" class="container-fluid">
		<h3 class="page-header">Produtos cadastrados</h3>
		<h2 class="msg">${msg }</h2>
		<div class="row">
			<div class="col-md-12">
				<a href="salvarProduto?acao=cadastrar"><button
						class="btn btn-primary" type="button">Cadastrar novo</button></a>
			</div>
		</div>
		<hr />
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#id</th>
						<th>#nome</th>
						<th>#descrição</th>
						<th>#valor</th>
						<th>#quantidade</th>
						<th>#editar</th>
						<th>#excluir</th>
					</tr>
				</thead>
				<c:forEach items="${produtos}" var="prod">
					<tr>
						<td style="width: 50px;"><c:out value="${prod.id}"></c:out></td>
						<td><c:out value="${prod.nome}"></c:out></td>
						<td><c:out value="${prod.descricao}"></c:out></td>
						<td style="width: 150px;"><fmt:formatNumber type="number" maxFractionDigits="2"  value="${prod.valor}"/></td>
						<td style="width: 150px;"><c:out value="${prod.quantidade}"></c:out></td>
						<td style="width: 50px;"><a href="editarProduto?acao=editar&prod=${prod.id}"><i
								class="far fa-edit"></i></a></td>
						<td style="width: 50px;"><a href="listarProduto?acao=delete&prod=${prod.id}"><i
								onclick="return confirm('Tem certeza que deseja excluir o produto ${prod.nome}?')" class="far fa-trash-alt"></i></a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>