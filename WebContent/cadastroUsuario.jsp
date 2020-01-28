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

<title>Cadastro de Usuário</title>
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
		<h3 class="page-header">Cadastro de Usuário</h3>
		<h2 class="msg">${msg }</h2>
		<form action="salvarUsuario" method="post"
			enctype="multipart/form-data">

			<div class="row">
				<input type="hidden" name="id" id="id" value="${user.id}">

				<div class="form-group col-md-12">
					<label class="control-label" for="nome">Nome</label> <input
						type="text" class="form-control" name="nome" required="required"
						id="nome" value="${user.nome}">

				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-2">
					<label class="control-label" for="cep">CEP</label> <input
						type="text" class="form-control" name="cep" required="required"
						id="cep" value="${user.cep}">

				</div>
				<div class="form-group col-md-4">
					<label class="control-label" for="rua">Rua</label> <input
						type="text" class="form-control" name="rua" required="required"
						id="rua" value="${user.rua}">

				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="bairro">Bairro</label> <input
						type="text" class="form-control" name="bairro" required="required"
						id="bairro" value="${user.bairro}">

				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="cidade">Cidade</label> <input
						type="text" class="form-control" name="cidade" required="required"
						id="cidade" value="${user.cidade}">

				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="uf">Estado</label> <input
						type="text" class="form-control" name="uf" required="required"
						id="uf" value="${user.uf}">

				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-2">
					<label class="control-label" for="perfil">Perfil</label> 
					<select class="form-control" name="perfil" id="perfil">
						<option>Selecione o perfil</option>
						<option value="administrador">Administrador</option>
						<option value="gerente">Gerente</option>
						<option value="secretario">Secretário(a)</option>
						<option value="funcionario">Funcionário</option>
					</select>					
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="sexo">Sexo</label> 
					<input required="required" type="radio" name="sexo" value="Masculino">Masculino</input>
					<input required="required" type="radio" name="sexo" value="Feminino">Feminino</input>					
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="ativo">Ativo</label> <input
						type="checkbox" name="ativo" id="ativo">

				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="login">Login</label> <input
						type="text" class="form-control" name="login" required="required"
						id="login" value="${user.login}">

				</div>
				<div class="form-group col-md-3">
					<label class="control-label" for="senha">Senha</label> <input
						type="password" class="form-control" name="senha"
						required="required" id="senha" value="${user.senha}">

				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-2">
					<label class="control-label" for="foto">Foto</label> <input
						type="file" class="form-control" name="foto" id="foto"
						value="${user.foto}">

				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="documento">Documento</label> <input
						type="file" class="form-control" name="documento" id="documento"
						value="${user.documento}">

				</div>
				
			</div>
			<hr />
			<div class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
					<a href="listarUsuario?acao=listarTodos"><button
							class="btn btn-default" type="button">Voltar</button></a>
				</div>
			</div>
		</form>
	</div>

</body>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/script.js"></script>
</html>