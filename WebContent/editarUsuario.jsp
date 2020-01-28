<%@page import="beans.BeanUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/estilos.css">

<script type="text/javascript">
	$(document).ready(
			function() {

				function limpa_formulário_cep() {
					// Limpa valores do formulário de cep.
					$("#rua").val("");
					$("#bairro").val("");
					$("#cidade").val("");
					$("#uf").val("");
				}

				//Quando o campo cep perde o foco.
				$("#cep").blur(
						function() {

							//Nova variável "cep" somente com dígitos.
							var cep = $(this).val().replace(/\D/g, '');

							//Verifica se campo cep possui valor informado.
							if (cep != "") {

								//Expressão regular para validar o CEP.
								var validacep = /^[0-9]{8}$/;

								//Valida o formato do CEP.
								if (validacep.test(cep)) {

									//Preenche os campos com "..." enquanto consulta webservice.
									$("#rua").val("...");
									$("#bairro").val("...");
									$("#cidade").val("...");
									$("#uf").val("...");

									//Consulta o webservice viacep.com.br/
									$.getJSON("https://viacep.com.br/ws/" + cep
											+ "/json/?callback=?", function(
											dados) {

										if (!("erro" in dados)) {
											//Atualiza os campos com os valores da consulta.
											$("#rua").val(dados.logradouro);
											$("#bairro").val(dados.bairro);
											$("#cidade").val(dados.localidade);
											$("#uf").val(dados.uf);
										} //end if.
										else {
											//CEP pesquisado não foi encontrado.
											limpa_formulário_cep();
											alert("CEP não encontrado.");
										}
									});
								} //end if.
								else {
									//cep é inválido.
									limpa_formulário_cep();
									alert("Formato de CEP inválido.");
								}
							} //end if.
							else {
								//cep sem valor, limpa formulário.
								limpa_formulário_cep();
							}
						});
			});
</script>

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
		<h3 class="page-header">Editar Usuário</h3>
		<h2 class="msg">${msg }</h2>
		<form action="editarUsuario" method="post"
			enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-md-1">
					<label class="control-label" for="id">Código:</label> <input
						type="text" class="form-control" readonly="readonly" name="id"
						id="id" value="${user.id}">

				</div>
			</div>
			<div class="row">
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
					<label class="control-label" for="perfil">perfil</label> 
					<select class="form-control" name="perfil" id="perfil">
						<option value="nao_informado"
						<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getPerfil().equalsIgnoreCase("nao_informado")){
								out.print("selected");
								out.print(" ");
							}
						}					
						%>	
						>Selecione o perfil</option>
						<option value="administrador" 						
						<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getPerfil().equalsIgnoreCase("Administrador")){
								out.print("selected");
								out.print(" ");
							}
						}					
						%>						
						>Administrador</option>
						<option value="gerente"
						<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getPerfil().equalsIgnoreCase("Gerente")){
								out.print("selected");
								out.print(" ");
							}
						}					
						%>
						>Gerente</option>
						<option value="secretario"
						<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getPerfil().equalsIgnoreCase("Secretario")){
								out.print("selected");
								out.print(" ");
							}
						}					
						%>
						>Secretário(a)</option>
						<option value="funcionario"
						<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getPerfil().equalsIgnoreCase("funcionario")){
								out.print("selected");
								out.print(" ");
							}
						}					
						%>
						>Funcionário</option>
					</select>					
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="sexo">Sexo</label> 
					<input type="radio" name="sexo" 					
					<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getSexo().equalsIgnoreCase("Masculino")){
								out.print("checked");
								out.print(" ");
							}
						}
					
					%>					
					value="Masculino">Masculino</input>
					<input type="radio" name="sexo" 					
					<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.getSexo().equalsIgnoreCase("Feminino")){
								out.print("checked");
								out.print(" ");
							}
						}
					
					%>					
					value="Feminino">Feminino</input>					
				</div>
				<div class="form-group col-md-2">
					<label class="control-label" for="ativo">Ativo</label> 
					<input type="checkbox" name="ativo" id="ativo" 
					<%
						if(request.getAttribute("user") != null){
							BeanUsuario user = (BeanUsuario) request.getAttribute("user");
							if(user.isAtivo()){
								out.print("checked=\"checked\"");
								out.print(" ");
							}
						}
					
					%>
					>
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
	<br>

</body>
</html>