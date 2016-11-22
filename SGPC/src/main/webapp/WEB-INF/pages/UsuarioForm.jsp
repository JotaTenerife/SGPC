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
<%@include file="templates/headSelector.jsp"%>
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
			<h1>
				Editar ${model}
				<c:out value="${objeto.codigo}" />
			</h1>
		</c:when>
		<c:otherwise>
			<h1>Crear ${model}</h1>
		</c:otherwise>
	</c:choose>

	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>

	<form:form method="POST" modelAttribute="objeto"
		class="form-horizontal" id="formulario">
		<form:input type="hidden" path="id" id="id" />
		<form:input type="hidden" disabled="disabled" path="fechaAlta"
			id="fechaAlta" class="form-control" />
		<div class="row">
			<spring:bind path="rol">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="rol" class="control-label">Rol <span class="requerido">*</span></label>
					<form:select path="rol" items="${roles}" multiple="false"
						itemValue="id" itemLabel="codigo" class="form-control" />
					<div class="has-error">
						<form:errors path="rol" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			
			<spring:bind path="codigo">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="codigo" class="control-label">Código <span class="requerido">*</span></label>
					<c:choose>
						<c:when test="${edit}">
							<form:input type="text" path="codigo" id="codigo"
								class="form-control" disabled="true" />
						</c:when>
						<c:otherwise>
							<form:input type="text" path="codigo" id="codigo"
								class="form-control" />
						</c:otherwise>
					</c:choose>
					<div class="has-error">
						<form:errors path="codigo" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="password">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="password" class="control-label">Password <span class="requerido">*</span></label>
					<form:input type="password" path="password" id="password"
						class="form-control" />
					<div class="has-error">
						<form:errors path="password" class="help-inline" />
					</div>
				</div>
			</spring:bind>
		</div>
		<div class="row">
			<spring:bind path="nifNie">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="nifNie" class="control-label">NIF/NIE <span class="requerido">*</span></label>
					<form:input type="text" path="nifNie" id="nifNie"
						class="form-control" />
					<div class="has-error">
						<form:errors path="nifNie" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="nombre">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="nombre" class="control-label">Nombre <span class="requerido">*</span></label>
					<form:input type="text" path="nombre" id="nombre"
						class="form-control" />
					<div class="has-error">
						<form:errors path="nombre" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="apellido1">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="apellido1" class="control-label">Apellido 1 <span class="requerido">*</span></label>
					<form:input type="text" path="apellido1" id="apellido1"
						class="form-control" />
					<div class="has-error">
						<form:errors path="apellido1" class="help-inline" />
					</div>
				</div>
			</spring:bind>
		</div>
		<div class="row">
			<spring:bind path="apellido2">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="apellido2" class="control-label">Apellido 2</label>
					<form:input type="text" path="apellido2" id="apellido2"
						class="form-control" />
					<div class="has-error">
						<form:errors path="apellido2" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="email">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="email" class="control-label">Email <span class="requerido">*</span></label>
					<form:input type="email" path="email" id="email"
						class="form-control" />
					<div class="has-error">
						<form:errors path="email" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="telefono">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="telefono" class="control-label">Teléfono</label>
					<form:input type="telefono" path="telefono" id="telefono"
						class="form-control" />
					<div class="has-error">
						<form:errors path="telefono" class="help-inline" />
					</div>
				</div>
			</spring:bind>
		</div>
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
	</form:form>

	<%@include file="templates/footer.jsp"%>
	<%@include file="templates/footer-selector.jsp"%> 
</body>
</html>