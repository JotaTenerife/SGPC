<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${edit}">
		<div id="ventanaModalDelete" class="modal fade" tabindex="-1"
			role="dialog">
	</c:when>
	<c:otherwise>
	<div id="ventanaModalDelete${vs.index}" class="modal fade"
			tabindex="-1" role="dialog">
		
	</c:otherwise>
</c:choose>
<div class="modal-dialog" role="document">
	<div class="modal-content">
		<div class="modal-body">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>

			<p>
				<span class="glyphicon glyphicon-exclamation-sign"></span> ¿Está
				seguro que desea borrar ${objeto.codigo} ?
			</p>

			<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			<a href=" <c:url value='${basepath}/${objeto.codigo}/borrar' />"
				class="btn btn-danger"> Borrar</a>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
