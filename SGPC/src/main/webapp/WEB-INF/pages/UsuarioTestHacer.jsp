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
		<li><a href="<c:url value="${basepath}/${objeto.codigo}" />">Tests</a></li>
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
		<c:if test="${numIntento == objeto.oportunidades}">
			<strong>No le quedan más oportunidades</strong>
		</c:if>
		<c:if test="${numIntento != objeto.oportunidades}">
			${numIntento + 1} de ${objeto.oportunidades}
		</c:if>
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

		<dt>Preguntas</dt>
		<dd>${numPreguntas}</dd>

		<dt>Resultado</dt>
		<c:if test="${aprobado}">
			<dd>Aprobado</dd>
		</c:if>
		<c:if test="${!aprobado && numIntento == objeto.oportunidades}">
			<dd>No Aprobado</dd>
		</c:if>
		<c:if test="${!aprobado && numIntento != objeto.oportunidades}">
			<dd>No Aprobado - Tiene intentos</dd>
		</c:if>
	</dl>

	<p class="text-right">
		<c:if test="${empty objeto.fechaBaja}">
			<a href="<c:url value='${basepath}' />"
				data-toggle="modal"
				class="btn btn-warning"><span class=""
				aria-hidden="true"></span> Salir</a>
		</c:if>
		<c:if test="${not empty objeto.fechaBaja}">
			<a href="<c:url value='${basepath}/${objeto.codigo}/reactivar' />"
				class="btn btn-success"><span
				class="glyphicon glyphicon-retweet" aria-hidden="true"></span>
				Comenzar</a>
		</c:if>

		<c:if test="${numIntento != objeto.oportunidades}">
			<a
				href="<c:url value='${basepath}/${objeto.codigo}/${primeraPregunta}' />"
				class="btn btn-success"><span class="" aria-hidden="true"></span>
				Comenzar</a>
		</c:if>

	</p>
	<%@include file="templates/modalWindowDelete.jsp"%>
	<%@include file="templates/footer.jsp"%>
</body>
</html>