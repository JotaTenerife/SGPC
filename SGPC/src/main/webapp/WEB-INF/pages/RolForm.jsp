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
				Editar ${model} ${objeto.codigo}
			</h1>
		</c:when>
		<c:otherwise>
			<h1>Crear ${model}</h1>
		</c:otherwise>
	</c:choose>

	<form:form method="POST" modelAttribute="objeto"
		class="form-horizontal">

		<form:input type="hidden" path="id" id="id" />

		<div class="form-group">
			<label for="codigo" class="col-sm-2 control-label">Código</label>
			<div class="col-sm-10">
				<form:input type="text" path="codigo" id="codigo"
					class="form-control" />
				<div class="has-error">
					<form:errors path="codigo" class="help-inline" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label for="descripcion" class="col-sm-2 control-label">Descripción</label>
			<div class="col-sm-10">
				<form:input type="text" path="descripcion" id="descripcion"
					class="form-control" />
				<div class="has-error">
					<form:errors path="descripcion" class="help-inline" />
				</div>
			</div>
		</div>
					
		<!-- Selección de roles -->
		<h3>Asignar permisos a rol</h3>
		<div class="form-group">
			<form:select path="permisos" items="${permisos}" itemValue="id"
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