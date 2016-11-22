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
<title>Usuario</title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li class="active">Error 401</li>
	</ol>

	<div class="error-template">
		<h1>Oops!</h1>
		<h2>Error 401: Acceso denegado</h2>
		<div class="error-details">Lo sentimos, pero no tiene permisos
			para acceder a la página solicitada.</div>
		<div class="error-actions">
			<a href="<c:url value='/' />" class="btn btn-success">Volver a la
				página de inicio</a>
		</div>
	</div>

	<%@include file="templates/footer.jsp"%>
</body>
</html>