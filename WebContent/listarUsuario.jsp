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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Usuários cadastrados</title>
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
				<li><a href="listarUsuario?acao=listarTodos">Usuários</a></li>
				<li><a href="salvarProduto?acao=listarTodos">Produtos</a></li>
				<li><a href="index.jsp">Sair</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div id="main" class="container-fluid">
		<h3 class="page-header">Usuários cadastrados</h3>
		<h2 class="msg">${msg }</h2>
		<div class="row">
			<div class="col-md-12">
				<a href="salvarUsuario?acao=cadastrar"><button
						class="btn btn-primary" type="button">Cadastrar novo</button></a>
			</div>
		</div>
		<br>
		<form action="pesquisaServlet" method="post">
			<div class="row">
				<div class="form-group col-md-4">
					<div class="row">
						<div class="col-md-8">
							<input type="text" placeholder="Pesquisa usuário"
								class="form-control" name="pesquisar" id="pesquisar">
						</div>
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary">Pesquisar</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<hr />
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#id</th>
						<th>#foto</th>
						<th>#documento</th>
						<th>#nome</th>
						<th>#telefones</th>
						<th>#editar</th>
						<th>#excluir</th>
					</tr>
				</thead>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td><c:out value="${user.id}"></c:out></td>
						<c:if test="${user.fotoMiniatura != null}">
							<td><a
								href="listarUsuario?acao=download&tipo=imagem&user=${user.id}">
									<img src='<c:out value="${user.fotoMiniatura}"/>'
									alt="imagem user" title="imagem user" width="22px"
									height="22px" />
							</a></td>
						</c:if>
						<c:if test="${user.fotoMiniatura == null}">
							<td><img src="resources/img/icon_user.jpg" alt="imagem user"
								title="imagem user" width="22px" height="22px" /></td>
						</c:if>
						<c:if test="${user.documento != null}">
							<td><a
								href="listarUsuario?acao=download&tipo=documento&user=${user.id}">Documento</a></td>
						</c:if>
						<c:if test="${user.documento == null}">
							<td>Documento</td>
						</c:if>
						<td><c:out value="${user.nome}"></c:out></td>
						<td><a href="salvarTelefone?acao=addFone&user=${user.id}"><i
								class="fas fa-phone"></i></a></td>
						<td><a href="editarUsuario?acao=editar&user=${user.id}"><i
								class="far fa-edit"></i></a></td>
						<td><a href="listarUsuario?acao=delete&user=${user.id}"><i
								onclick="return confirm('Tem certeza que deseja excluir o usuário ${user.id}?')"
								class="far fa-trash-alt"></i></a></td>

					</tr>
				</c:forEach>
			</table>

		</div>

	</div>
</body>
</html>