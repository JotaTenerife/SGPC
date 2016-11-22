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
<title>${model}</title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li class="active">${model}</li>
	</ol>

	<h1>
		${model} <a href="<c:url value='${basepath}/crear' />"
			class="btn btn-primary"><span class="glyphicon glyphicon-plus"
			aria-hidden="true"></span> Crear</a>
	</h1>

	<c:if test="${not empty success}">
		<div class="alert alert-success" role="alert">${success}</div>
	</c:if>

	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>

	<table id="listado" class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nombre</th>
				<th>Apellido 1</th>
				<th>Apellido 2</th>
				<th>Email</th>
				<th>Fecha Baja</th>
				<th width="200">Operaciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${objetos}" var="objeto" varStatus="vs">
				<tr>
					<td>${objeto.codigo}</td>
					<td>${objeto.nombre}</td>
					<td>${objeto.apellido1}</td>
					<td>${objeto.apellido2}</td>
					<td>${objeto.email}</td>
					<td><c:out value="${objeto.fechaBaja}" /></td>
					<td><c:choose>
							<c:when test="${objeto.isActivo()}">
								<a href="<c:url value='${basepath}/${objeto.codigo}' />"
									class="btn btn-primary btn-xs"><span
									class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
									Ver</a>
								<a href="<c:url value='${basepath}/${objeto.codigo}/editar' />"
									class="btn btn-success btn-xs"><span
									class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									Editar</a>
								<a href="#ventanaModalDelete${vs.index}"
									data-toggle="modal"
									class="btn btn-danger btn-xs"><span
									class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									Borrar</a>
								<%@include file="templates/modalWindowDelete.jsp"%>
								
							</c:when>
							<c:otherwise>
								<a href="<c:url value='${basepath}/${objeto.codigo}' />"
									class="btn btn-primary btn-xs"><span
									class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
									Ver</a>
								<a
									href="<c:url value='${basepath}/${objeto.codigo}/reactivar' />"
									class="btn btn-warning btn-xs"><span
									class="glyphicon glyphicon-retweet" aria-hidden="true"></span>
									Reactivar</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%@include file="templates/footer.jsp"%>
</body>
</html>