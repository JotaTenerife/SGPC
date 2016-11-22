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
<title>${model} <c:out value="${objeto.codigo}" /></title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<ol class="breadcrumb">
		<li><a href="<c:url value="/" />">Inicio</a></li>
		<li><a href="<c:url value="/gestion" />">Gestión</a></li>
		<li><a href="<c:url value="${basepath}" />">${model}</a></li>
		<li class="active">${objeto.codigo}</li>
	</ol>

	<h1>${model} ${objeto.codigo}</h1>

	<dl class="dl-horizontal">
	
		<dt>Código</dt>
		<dd>${objeto.codigo}</dd>
		
		<dt>Texto</dt>
		<dd>${objeto.texto}</dd>

		<dt>Ruta Imagen</dt>
		<dd>${objeto.imagen}</dd>

		<c:if test="${not empty objeto.fechaBaja}">
			<dt>Fecha baja</dt>
			<dd>${objeto.fechaBaja}</dd>
		</c:if>
		
		<dt>Respuestas</dt>
		<dd><ul>
			<c:forEach items="${objeto.respuestas}" var="respuesta">
					<li>${respuesta.texto}
					<c:if test="${respuesta.correcta==1}"> -- <strong>Correcta: Si</strong>
					</c:if>
					<c:if test="${respuesta.correcta==0}"> -- Correcta: No
					</c:if>
					</li>
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
		
	</dl>
		<!-- IMAGEN -->
		<div class="container" style="margin-top: 15px">
			<img src="${objeto.imagen}" class="img-responsive center-block"
				style="max-height:200px">
		</div>

	<p class="text-right" style="margin-bottom: 15px">
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