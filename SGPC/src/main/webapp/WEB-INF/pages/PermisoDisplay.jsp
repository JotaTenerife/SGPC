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
<title>Permiso <c:out value="${objeto.codigo}" /></title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li><a href="<c:url value="${basepath}" />">Permiso</a></li>
		<li class="active"><c:out value="${objeto.codigo}" /></li>
	</ol>

	<h1>
		Permiso
		<c:out value="${objeto.codigo}" />
	</h1>

	<dl class="dl-horizontal">
		<dt>Código</dt>
		<dd>
			<c:out value="${objeto.codigo}" />
		</dd>
		<dt>Descripción</dt>
		<dd>
			<c:out value="${objeto.descripcion}" />
		</dd>
		<dt>Roles</dt>
		<dd><ul>
			<c:forEach items="${objeto.roles}" var="rol">
				<li><c:out value="${rol.codigo}" /></li>
			</c:forEach>
			</ul>
		</dd>
	</dl>

	<p class="text-right">
		<a href="<c:url value='${basepath}/${objeto.codigo}/editar' />"
			class="btn btn-success"><span
			class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Editar</a>
	</p>

	<%@include file="templates/footer.jsp"%>
</body>
</html>