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

	<div class="jumbotron">
      <div class="container">
      	<img src="<c:url value='/static/img/llamalox.png' />" />
        <h1>Llámalo X</h1>
        <p>Sistema de gestión de pruebas de conocimiento</p>
      </div>
    </div>

	<%@include file="templates/footer.jsp"%>
</body>
</html>