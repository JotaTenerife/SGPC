<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="templates/head.jsp"%>
<title></title>
</head>
<body>

	<div class="form-group">

		<c:out value="${message}" />
		<form:form method="POST" action="greet?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data"
			commandName="fileFormBean">
			<table>
				<tr>
					<td>Selecciona fichero:</td>
					<td><input type="file" name="fichero" /></td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Subir fichero"></td>
				</tr>
			</table>
		</form:form>

	</div>


	<%@include file="templates/footer.jsp"%>
</body>
</html>