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
<%@include file="templates/headDatetimepicker.jsp"%>
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

		<form:input type="hidden" path="id" id="id" />

		<div class="row">

			<spring:bind path="codigo">
				<div class="col-xs-2 ${status.error ? 'has-error' : ''}">
					<label for="codigo" class="control-label">Código <span
						class="requerido">*</span></label>
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

			<spring:bind path="nombre">
				<div class="col-xs-5 ${status.error ? 'has-error' : ''}">
					<label for="nombre" class="control-label">Nombre <span
						class="requerido">*</span></label>
					<form:input type="text" path="nombre" id="nombre"
						class="form-control" />
					<div class="has-error">
						<form:errors path="nombre" class="help-inline" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="oportunidades">
				<div class="col-xs-2 ${status.error ? 'has-error' : ''}">
					<label for="oportunidades" class="control-label">Oportunidades
						<span class="requerido">*</span>
					</label>
					<form:input type="number" path="oportunidades" id="oportunidades"
						class="form-control" />
					<div class="has-error">
						<form:errors path="oportunidades" class="help-inline" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="fechaInicio">
				<div class="col-xs-3 ${status.error ? 'has-error' : ''}">
					<label for="fechaInicio" class="control-label">Fecha Inicio
						<span class="requerido">*</span>
					</label>
					<div id="datetimepicker2" class="input-group">
						<form:input type="text" path="fechaInicio" id="fechaInicio"
							class="form-control" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
					<div class="has-error">
						<form:errors path="fechaInicio" class="help-inline" />
					</div>
					<script type="text/javascript">
						$(function() {
							$('#datetimepicker2').datetimepicker({
								locale : 'es',
								format : 'DD/MM/YYYY HH:mm:ss'
							});
						});
					</script>
				</div>
			</spring:bind>
		</div>

		<div class="row">

			<spring:bind path="descripcion">
				<div class="col-xs-9 ${status.error ? 'has-error' : ''}">
					<label for="descripcion" class="control-label">Descripción
						<span class="requerido">*</span>
					</label>
					<form:input type="text" path="descripcion" id="descripcion"
						class="form-control" />
					<div class="has-error">
						<form:errors path="descripcion" class="help-inline" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="fechaFin">
				<div class="col-xs-3 ${status.error ? 'has-error' : ''}">
					<label for="fechaFin" class="control-label">Fecha Fin <span
						class="requerido">*</span></label>
					<div id="datetimepicker3" class="input-group">
						<form:input type="text" path="fechaFin" id="fechaFin"
							class="form-control" />
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-calendar"></span>
						</span>
					</div>
					<div class="has-error">
						<form:errors path="fechaFin" class="help-inline" />
					</div>
					<script type="text/javascript">
						$(function() {
							$('#datetimepicker3').datetimepicker({
								locale : 'es',
								format : 'DD/MM/YYYY HH:mm:ss'
							});
						});
					</script>
				</div>
			</spring:bind>

		</div>

		<!-- Selección de preguntas -->
		<h3>Asignar preguntas a test</h3>
		<div class="form-group">
			<c:choose>
				<c:when test="${tieneIntentos}">
					<form:select disabled="true" path="preguntas" items="${preguntas}"
						itemValue="id" itemLabel="codigo" multiple='multiple'
						class="searchable" name="searchable[]" />
				</c:when>
				<c:otherwise>
					<form:select path="preguntas" items="${preguntas}" itemValue="id"
						itemLabel="codigo" multiple='multiple' class="searchable"
						name="searchable[]" />
				</c:otherwise>
			</c:choose>
		</div>
		<!-- ends -->

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
	<%@include file="templates/footer-selector.jsp"%>
</body>
</html>