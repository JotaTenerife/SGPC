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
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<title>Login</title>
</head>
<body>
	<%@include file="templates/nav.jsp"%>

	<div class="panel panel-primary login-container">
		<div class="panel-heading">Login</div>
		<div class="panel-body">
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post" class="form-horizontal">
				<c:if test="${param.error != null}">
					<div class="alert alert-danger">
						<p>Nombre de usuario o contraseña no válidos.</p>
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">
						<p>Ha salido del sistema satisfactoriamente.</p>
					</div>
				</c:if>
				<div class="input-group input-sm">
					<label class="input-group-addon" for="username"><i
						class="fa fa-user"></i></label> <input type="text" class="form-control"
						id="username" name="ssoId" placeholder="Usuario" required>
				</div>
				<div class="input-group input-sm">
					<label class="input-group-addon" for="password"><i
						class="fa fa-lock"></i></label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="Contraseña" required>
				</div>
				<div class="input-group input-sm">
					<div class="checkbox">
						<label><input type="checkbox" id="rememberme"
							name="remember-me"> Recuérdame</label>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div class="form-actions">
					<input type="submit" class="btn btn-block btn-primary btn-default"
						value="Log in">
				</div>
			</form>
		</div>
	</div>
	<%@include file="templates/footer.jsp"%>
</body>
</html>