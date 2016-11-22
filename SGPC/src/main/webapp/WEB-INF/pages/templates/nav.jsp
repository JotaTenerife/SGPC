<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Llámalo X</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/tests" />">Tests</a></li>
				<sec:authorize
					access="hasRole('MODULO_USUARIOS') or hasRole('MODULO_ROLES') or hasRole('MODULO_PERMISOS') or hasRole('MODULO_TESTS') or hasRole('MODULO_PREGUNTAS')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Gestión<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<sec:authorize access="hasRole('MODULO_USUARIOS')">
								<li <c:if test="${model=='Usuario'}">class="active"</c:if>><a
									href="<c:url value="/gestion/usuario" />">Usuarios</a></li>
							</sec:authorize>
							<sec:authorize access="hasRole('MODULO_ROLES')">
								<li <c:if test="${model=='Rol'}">class="active"</c:if>><a
									href="<c:url value="/gestion/rol" />">Roles</a></li>
							</sec:authorize>
							<sec:authorize access="hasRole('MODULO_PERMISOS')">
								<li <c:if test="${model=='Permiso'}">class="active"</c:if>><a
									href="<c:url value="/gestion/permiso" />">Permisos</a></li>
							</sec:authorize>
							<sec:authorize
								access="hasRole('MODULO_PERMISOS') or hasRole('MODULO_ROLES') or hasRole('MODULO_USUARIOS')">
								<li role="separator" class="divider"></li>
							</sec:authorize>
							<sec:authorize access="hasRole('MODULO_TESTS')">
								<li <c:if test="${model=='Test'}">class="active"</c:if>><a
									href="<c:url value="/gestion/test" />">Tests</a></li>
							</sec:authorize>
							<sec:authorize access="hasRole('MODULO_PREGUNTAS')">
								<li <c:if test="${model=='Pregunta'}">class="active"</c:if>><a
									href="<c:url value="/gestion/pregunta" />">Preguntas</a></li>
							</sec:authorize>
							<sec:authorize access="hasRole('MODULO_TESTS')">
								<li <c:if test="${model=='Resultados'}">class="active"</c:if>><a
									href="<c:url value="/gestion/resultado" />">Resultados</a></li>
							</sec:authorize>
						</ul></li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Bienvenido ${loggedinuser}<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<sec:authorize access="isFullyAuthenticated()">
							<li><a href="<c:url value="/logout" />">Logout</a></li>
						</sec:authorize>
						<sec:authorize access="isAnonymous()">
							<li><a href="<c:url value="/login" />">Login</a></li>
						</sec:authorize>
					</ul></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

<div class="container">