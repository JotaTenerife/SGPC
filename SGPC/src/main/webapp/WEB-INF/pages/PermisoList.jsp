<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="templates/head.jsp"%>
<%@include file="templates/head-listados.jsp"%>
<title>Permisos</title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li class="active">Permiso</li>
	</ol>

	<h1>Permisos</h1>

	<c:if test="${not empty success}">
		<div class="alert alert-success" role="alert">${success}</div>
	</c:if>

	<c:if test="${not empty fail}">
		<div class="alert alert-danger" role="alert">${fail}</div>
	</c:if>

	<table id="listado" class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Descripción</th>
				<th width="200">Operaciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${objetos}" var="objeto" >
				<tr>
					<td>${objeto.codigo}</td>
					<td>${objeto.descripcion}</td>
					<td><a href="<c:url value='${basepath}/${objeto.codigo}' />"
						class="btn btn-primary btn-xs"><span
							class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
							Ver</a> <a
						href="<c:url value='${basepath}/${objeto.codigo}/editar' />"
						class="btn btn-success btn-xs"><span
							class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							Editar</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%@include file="templates/footer.jsp"%>

</body>
</html>