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
<title><c:choose>
		<c:when test="${edit}">
			Editar ${model} ${objeto.codigo}
		</c:when>
		<c:otherwise>
			Crear ${model}
		</c:otherwise>
	</c:choose></title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li><a href="<c:url value="${basepath}" />">${model}</a></li>
		<c:choose>
			<c:when test="${edit}">
				<li><a href="<c:url value="${basepath}/${objeto.codigo}" />"><c:out
							value="${objeto.codigo}" /></a></li>
				<li class="active">Editar</li>
			</c:when>
			<c:otherwise>
				<li class="active">Crear</li>
			</c:otherwise>
		</c:choose>
	</ol>

	<c:choose>
		<c:when test="${edit}">
			<h1>Editar ${model} ${objeto.codigo}</h1>
		</c:when>
		<c:otherwise>
			<h1>Crear ${model}</h1>
		</c:otherwise>
	</c:choose>

	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>

	<form:form method="POST" modelAttribute="objeto"
		class="form-horizontal">

		<form:input type="hidden" path="pregunta" id="pregunta" value="1" />
		<form:input type="hidden" path="intentos" id="intentos" value="0" />
		<form:input type="hidden" path="acierto" id="acierto" value="0" />

		<div class="row">

			<spring:bind path="usuario">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="usuario" class="control-label">Usuario <span
						class="requerido">*</span></label>
					<form:select path="usuario" items="${usuarios}" multiple="false"
						itemValue="id" itemLabel="codigo" class="form-control" />
					<div class="has-error">
						<form:errors path="usuario" class="help-inline" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="test">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="test" class="control-label">Test <span
						class="requerido">*</span></label>
					<form:select path="test" items="${tests}" multiple="false"
						itemValue="id" itemLabel="codigo" class="form-control" />
					<div class="has-error">
						<form:errors path="test" class="help-inline" />
					</div>
				</div>
			</spring:bind>

		</div>

		<div class="form-group">
			<div class="col-xs-12 operacionesFull text-right">
				<c:choose>
					<c:when test="${edit}">
						<a class="btn btn-warning" href="<c:url value='${basepath}' />">Cancelar</a>
						<input type="submit" value="Actualizar" class="btn btn-success" />
					</c:when>
					<c:otherwise>
						<a class="btn btn-warning" href="<c:url value='${basepath}' />">Cancelar</a>
						<input type="submit" value="Guardar" class="btn btn-success" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>

	</form:form>

	<%@include file="templates/footer.jsp"%>
</body>
</html>