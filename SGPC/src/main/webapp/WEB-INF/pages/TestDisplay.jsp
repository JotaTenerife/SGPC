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
<title>Usuario <c:out value="${objeto.codigo}" /></title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li><a href="<c:url value="${basepath}" />">Test</a></li>
		<li class="active"><c:out value="${objeto.codigo}" /></li>
	</ol>

	<h1>
		Test
		<c:out value="${objeto.codigo}" />
	</h1>

	<dl class="dl-horizontal">
		<dt>Código</dt>
		<dd>
			<c:out value="${objeto.codigo}" />
		</dd>
		<dt>Nombre</dt>
		<dd>
			<c:out value="${objeto.nombre}" />
		</dd>

		<dt>Descripción</dt>
		<dd>
			<c:out value="${objeto.descripcion}" />
		</dd>

		<dt>Oportunidades</dt>
		<dd>
			<c:out value="${objeto.oportunidades}" />
		</dd>

		<dt>Fecha Inicio</dt>
		<dd>
			<fmt:formatDate value="${objeto.fechaInicio}"
				pattern="dd/MM/yyyy HH:mm:ss" />
		</dd>

		<dt>Fecha Fin</dt>
		<dd>
			<fmt:formatDate value="${objeto.fechaFin}"
				pattern="dd/MM/yyyy HH:mm:ss" />
		</dd>

		<c:if test="${not empty objeto.fechaBaja}">
			<dt>Fecha baja</dt>
			<dd>
				<c:out value="${objeto.fechaBaja}" />
			</dd>
		</c:if>
		
		<dt>Usuarios</dt>
		<dd><ul>
			<c:forEach items="${objeto.usuarios}" var="usuario">
				<li>${usuario.codigo} - ${usuario.nombre} ${usuario.apellido1} ${usuario.apellido2}</li>
			</c:forEach>
			</ul>
		</dd>
		
<!-- 		<dt>Intentos asignados</dt> -->
<!-- 		<dd><ul> -->
<%-- 			<c:forEach items="${objeto.intentos}" var="intento"> --%>
<%-- 				<li>${intento}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</dd> -->
		
		<dt>Preguntas</dt>
		<dd><ul>
			<c:forEach items="${objeto.preguntas}" var="pregunta">
				<li>${pregunta.texto}</li>
			</c:forEach>
			</ul>
		</dd>
	</dl>

	<p class="text-right">
		<c:if test="${empty objeto.fechaBaja}">
			<a href="#ventanaModalDelete"
				data-toggle="modal"
				class="btn btn-danger"><span class="glyphicon glyphicon-trash"
				aria-hidden="true"></span> Borrar</a>
		</c:if>
		<c:if test="${not empty objeto.fechaBaja}">
			<a href="<c:url value='${basepath}/${objeto.codigo}/reactivar' />"
				class="btn btn-success"><span
				class="glyphicon glyphicon-retweet" aria-hidden="true"></span>
				Reactivar</a>
		</c:if>
		<a href="<c:url value='${basepath}/${objeto.codigo}/editar' />"
			class="btn btn-success"><span class="glyphicon glyphicon-pencil"
			aria-hidden="true"></span> Editar</a>
	</p>
	<%@include file="templates/modalWindowDelete.jsp"%>
	<%@include file="templates/footer.jsp"%>
</body>
</html>