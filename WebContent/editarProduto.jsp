<%@page import="beans.BeanCategoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Editar produto</title>
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
		<h3 class="page-header">Editar produto</h3>
		<h2 class="msg">${msg }</h2>
		<form action="editarProduto" method="post">
			<div class="row">
				<div class="form-group col-md-1">
					<label class="control-label" for="nome">Id</label><input
						type="text" class="form-control" readonly="readonly" name="id"
						id="id" value="${prod.id}">
				</div>
				<div class="form-group col-md-6">
					<label class="control-label" for="nome">Nome</label>
					<div class="controls">
						<input type="text" class="form-control" name="nome" id="nome"
							required="required" value="${prod.nome}">
					</div>
				</div>
				<div class="form-group col-md-5">
					<label class="control-label" for="descricao">Descrição</label>
					<div class="controls">
						<input type="text" class="form-control" name="descricao"
							required="required" id="descricao" value="${prod.descricao}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-4">
					<label class="control-label" for="valor">Valor</label>
					<div class="controls">
						<input type="text" class="form-control" name="valor" id="valor" 
							required="required" value="${prod.valorEmTexto}">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label class="control-label" for="quantidade">Quantidade</label>
					<div class="controls">
						<input type="text" class="form-control" name="quantidade"
							required="required" id="quantidade" maxlength="3" value="${prod.quantidade}">
					</div>
				</div>
				<div class="form-group col-md-4">
					<label class="control-label" for="categoria">Categoria</label>
					<div class="controls">
						<select class="form-control" name="categoria_id" id="categoria">
						<option value="nao_informado"
						
						<%
						if(request.getAttribute("cat") != null){
							BeanCategoria cat = (BeanCategoria) request.getAttribute("cat");
							if(cat.getNome().equalsIgnoreCase("nao_informado")){
								out.print("selected");
								out.print(" ");
							}
						}					
						%>	
						
						>Selecione a categoria</option>
						<c:forEach items="${categoria}" var="cat">
							<option value="${cat.id}" id="${cat.id}"
							<c:if test="${cat.id == prod.categoria_id}">
								<c:out value="selected=selected"/>
							</c:if>
							>${cat.nome}</option>
						</c:forEach>
					</select>
					</div>
				</div>
			</div>

			<hr />
			<div class="row">
				<div class="col-md-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
					<a href="salvarProduto?acao=listarTodos"><button class="btn"
							type="button">Voltar</button></a>
				</div>
			</div>
		</form>
	</div>

</body>
</html>