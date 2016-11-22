<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="templates/head.jsp"%>
<%@include file="templates/head-listados.jsp"%>
<title>Resultados</title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li class="active">Resultado</li>
	</ol>

	<c:if test="${not empty success}">
		<div class="alert alert-success" role="alert">${success}</div>
	</c:if>

	<c:if test="${not empty fail}">
		<div class="alert alert-danger" role="alert">${fail}</div>
	</c:if>

	<table id="listado" class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Codigo</th>
				<th>Nombre</th>
				<th>Descripción</th>
				<th>Oportunidades</th>
				<th width="200">Operaciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${objetos}" var="objeto" varStatus="vs">
				<tr>
					<td>${objeto.codigo}</td>
					<td>${objeto.nombre}</td>
					<td>${objeto.descripcion}</td>
					<td>${objeto.oportunidades}</td>
					
					<td><%-- c:choose>
							<c:when test="${not test.isActivo()}">
								<a href="<c:url value='#' />" class="btn btn-danger btn-xs"><span
									class="glyphicon glyphicon-remove" aria-hidden="true"></span>
									Eliminado</a>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${test.isEnFecha()}">
										<a href="<c:url value='${basepath}/${test.codigo}' />"
											class="btn btn-primary btn-xs"><span
											class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
											Comenzar</a>
									</c:when>
									<c:otherwise>
										<a href="<c:url value='#' />" class="btn btn-warning btn-xs"><span
											class="glyphicon glyphicon-time" aria-hidden="true"></span>
											Fuera de fecha</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%@include file="templates/footer.jsp"%>
</body>
</html>