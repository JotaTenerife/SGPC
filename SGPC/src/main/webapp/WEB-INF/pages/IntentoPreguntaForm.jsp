<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="templates/head.jsp"%>
<title>Pregunta ${objeto.pregunta.codigo} del test
	${objeto.test.codigo}</title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li class="active">Pregunta</li>
	</ol>

	<h1>Pregunta ${objeto.pregunta.codigo} del test
		${objeto.test.codigo}</h1>

	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>

	<h2><p>${objeto.pregunta.texto}</p></h2>

	<div class="container" style="margin-top: 15px">
		<img src="${objeto.pregunta.imagen}"
			class="img-responsive center-block" style="max-height: 200px">
	</div>

	<form:form method="POST" modelAttribute="objeto" id="formulario">

		<div class="col-xs-12">
			<label for="respuesta" class="control-label">Respuestas <span
				class="requerido">*</span></label>
			<form:select path="respuesta" items="${respuestas}"
				multiple="false" itemValue="id" itemLabel="texto"
				class="form-control" />
		</div>

		<div class="form-group">
			<div class="col-xs-12 operacionesFull text-right">
				<a class="btn btn-warning" href="<c:url value='${basepath}' />">Abandonar</a>
				<input type="submit" value="Enviar" class="btn btn-success" />
			</div>
		</div>

	</form:form>

	<%@include file="templates/footer.jsp"%>
</body>