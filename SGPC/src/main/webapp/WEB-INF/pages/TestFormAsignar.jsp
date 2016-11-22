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
			Asignar usuarios a ${model} ${objeto.codigo}
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
				<li class="active">Asignar</li>
			</c:when>
			<c:otherwise>
				<li class="active">Crear</li>
			</c:otherwise>
		</c:choose>
	</ol>

	<c:choose>
		<c:when test="${edit}">
			<h1>Asignar usuarios a ${model} ${objeto.codigo}</h1>
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
		<form:input type="hidden" path="codigo" id="codigo" />
		<form:input type="hidden" path="nombre" id="nombre" />
		<form:input type="hidden" path="descripcion" id="descripcion" />

		<div class="row">
		
		</div>

		<!-- Selección de usuarios -->
		<h3>Asignar usuarios a test</h3>
		<div class="form-group">
			<form:select path="usuarios" items="${usuarios}" itemValue="id"
				itemLabel="codigo" multiple='multiple' class="searchable"
				name="searchable[]" />
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