<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<title>Cadastro de Telefones</title>
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
				<li><a href="acessoliberado.jsp">Início</a></li>
				<li><a href="salvarUsuario?acao=listarTodos">Usuários</a></li>
				<li><a href="salvarProduto?acao=listarTodos">Produtos</a></li>
				<li><a href="index.jsp">Sair</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div id="main" class="container-fluid">
		<h3 class="page-header">Cadastro de Telefones</h3>
		<h2 class="msg">${msg }</h2>
		<form action="salvarTelefone" method="post">

			<div class="row">
				<div class="form-group col-md-1">
					<label class="control-label" for="id">Cód. Usuário:</label>
					<div class="controls">
						<input type="text" class="form-control" readonly="readonly"
							name="id" id="id" value="${userEscolhido.id}">
					</div>
				</div>
				<div class="form-group col-md-5">
					<label class="control-label" for="nome">Nome</label>
					<div class="controls">
						<input type="text" class="form-control" readonly="readonly"
							name="nome" id="nome" value="${userEscolhido.nome}">
					</div>
				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="numero">Número</label>
					<div class="controls">
						<input type="text" class="form-control" name="numero"
							required="required" id="numero" value="">
					</div>
				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="tipo">Tipo</label>
					<div class="controls">
						<select name="tipo" class="form-control" required="required"
							id="tipo">
							<option></option>
							<option>Casa</option>
							<option>Trabalho</option>
							<option>Celular</option>
						</select>
					</div>
				</div>

				<div class="col-md-12 mt-20">
					<button type="submit" class="btn btn-primary">Salvar Telefone</button>
					<a href="salvarUsuario?acao=listarTodos"><button class="btn"
							type="button">Voltar</button></a>
				</div>
			</div>
		</form>

	</div>
	<hr/>
	<div id="main" class="container-fluid">
		<h3>Telefones cadastrados</h3>
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#id</th>
						<th>#número</th>
						<th>#tipo</th>
						<th>#excluir</th>
					</tr>
				</thead>
				<c:forEach items="${telefone}" var="fone">
					<tr>
						<td><c:out value="${fone.id}"></c:out></td>
						<td><c:out value="${fone.numero}"></c:out></td>
						<td><c:out value="${fone.tipo}"></c:out></td>
						<td><a
							href="salvarTelefone?acao=deleteFone&foneId=${fone.id}"> <i
								onclick="return confirm('Tem certeza que quer excluir o telefone ${fone.numero}?')" class="far fa-trash-alt"></i>
						</a></td>

					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>