<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="templates/head.jsp"%>
<title><c:choose>
		<c:when test="${edit}">
			Editar ${model} ${objeto.codigo}
		</c:when>
		<c:otherwise>
			Crear ${model}
		</c:otherwise>
	</c:choose></title>
</head>
<script type="text/javascript">

	// Genera un UUID único
	function generateUUID() {
	    var d = new Date().getTime();
	    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	        var r = (d + Math.random()*16)%16 | 0;
	        d = Math.floor(d/16);
	        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
	    });
	    return uuid;
	};

    $(function() {

        // inicializa el íncide
        var index = ${fn:length(objeto.respuestas)};

        // Añade una nueva respuesta
        $("#add").off("click").on("click", function() {
            $(this).before(function() {
                var html = '<div id="respuesta' + index + '" class="row">';                    
                html += '<input type="hidden" name="respuestas[' + index + '].codigo" value="' + generateUUID() + '" />';
                html += '<div class="col-xs-9"><label for="respuesta_' + index + '_texto" class="control-label">Texto</label><input type="text" name="respuestas[' + index + '].texto" id="respuesta_' + index + '_texto" class="form-control" /></div>';
				html += '<div class="col-xs-1"><label for="respuesta_' + index + '_correcta" class="control-label">Correcta</label><select id="respuesta_' + index + '_correcta" name="respuestas[' + index + '].correcta" class="form-control"><option value="0" selected="selected">No</option><option value="1">Sí</option></select></div>';
                html += '<div class="col-xs-2"><label class="control-label">Operaciones</label><a href="#" class="pregunta.remove btn btn-danger" data-index="' + index + '"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Borrar</a> </div>';
                html += "</div>";
                return html;
            });
            $("#respuesta" + index ).show();
            index++;
            return false;
        });

        // Borra el texto, asigna como falsa y oculta la respuesta
        $("div").off("click").on("click", ".pregunta\\.remove", function() {
            var index2remove = $(this).data("index");
            $("#respuesta_" + index2remove + "_texto").val("");
            $("#respuesta_" + index2remove + "_correcta").val(0);
            $("#respuesta" + index2remove).hide();
            return false;
        });

    });
    </script>
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
			<h1>Editar ${model} ${objeto.codigo}</h1>
		</c:when>
		<c:otherwise>
			<h1>Crear ${model}</h1>
		</c:otherwise>
	</c:choose>

	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>

	<form:form method="POST" modelAttribute="objeto" action="?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data"
		class="form-horizontal">
		<form:input type="hidden" path="id" id="id" />
		<div class="row">
			<spring:bind path="codigo">
				<div class="col-xs-2 ${status.error ? 'has-error' : ''}">
					<label for="codigo" class="control-label">Código <span
						class="requerido">*</span></label>
					<c:choose>
						<c:when test="${edit}">
							<form:input type="text" path="codigo" id="codigo"
								class="form-control" disabled="true" />
						</c:when>
						<c:otherwise>
							<form:input type="text" path="codigo" id="codigo"
								class="form-control" />
						</c:otherwise>
					</c:choose>
					<div class="has-error">
						<form:errors path="codigo" class="help-inline" />
					</div>
				</div>
			</spring:bind>
			<spring:bind path="texto">
				<div class="col-xs-6 ${status.error ? 'has-error' : ''}">
					<label for="texto" class="control-label">Texto <span
						class="requerido">*</span></label>
					<form:input type="text" path="texto" id="texto"
						class="form-control" />
					<div class="has-error">
						<form:errors path="texto" class="help-inline" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="imagen">
				<div class="col-xs-4 ${status.error ? 'has-error' : ''}">
					<label for="imagen" class="control-label">Imagen</label>
					<form:input type="text" path="imagen" id="imagen"
						class="form-control" />
					<div class="has-error">
						<form:errors path="imagen" class="help-inline" />
					</div>
				</div>
			</spring:bind>

		</div>
		
		<!--  Seleccionar Archivo -->
			<div class="container">
			<div class="col-xs-4 pull-right">
				<div class="form-group">
					<input type="file" name="fichero" accept="image/*" class="btn btn-primary " style="margin-top: 15px" />
				</div>
			</div>
			</div>
			
		
		<!-- IMAGEN -->
		<div class="container" style="margin-top: 15px">
			<img src="${objeto.imagen}" class="img-responsive center-block"
				style="max-height:200px">
		</div>


		<spring:bind path="respuestas">
			<h2>Respuestas</h2>
			<div class="has-error">
				<form:errors path="respuestas" class="help-inline" />
			</div>
		</spring:bind>
		<c:forEach items="${objeto.respuestas}" varStatus="loop">
			<div id="respuesta${loop.index}" class="row">
				<form:input type="hidden" path="respuestas[${loop.index}].codigo" />
				<div class="col-xs-9">
					<label for="respuesta_${loop.index}_texto" class="control-label">Texto</label>
					<form:input type="text" path="respuestas[${loop.index}].texto"
						id="respuesta_${loop.index}_texto" class="form-control" />
				</div>
				<div class="col-xs-1">
					<label for="respuesta_${loop.index}_correcta" class="control-label">Correcta</label>
					<form:select path="respuestas[${loop.index}].correcta"
						items="${correcta}" multiple="false" class="form-control"
						id="respuesta_${loop.index}_correcta" />
				</div>
				<div class="col-xs-2">
					<label class="control-label">Operaciones</label> <a href="#"
						class="pregunta.remove btn btn-danger" data-index="${loop.index}"><span
						class="glyphicon glyphicon-trash" aria-hidden="true"></span>
						Borrar</a>
				</div>
			</div>
			
		</c:forEach>
		<button id="add" class="btn btn-primary" type="button"
			style="margin-top: 20px;">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			Añadir respuesta
		</button>
		<div class="row">
			<div class="col-xs-12 operacionesFull text-right" style="margin-bottom: 15px">
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
</body>
</html>