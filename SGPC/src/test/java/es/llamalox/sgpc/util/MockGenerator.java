/**
 * 
 */
package es.llamalox.sgpc.util;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Respuesta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.model.usuarios.Usuario;

/**
 * @author JuanJo Yanes
 *
 */
public class MockGenerator {
	
	/**
	 * 
	 * @return
	 */
	public static Usuario crearUsuario() {
		Usuario usuario = new Usuario();
		Rol rol = crearRol(3,"Cod1G0Rol", "Descripción del Rol");
		Set<Intento> intentos = crearIntentos(usuario);
		
		usuario.setNombre("John");
		usuario.setId(4);
		usuario.setApellido1("Doe1");
		usuario.setApellido2("Doe2");
		usuario.setCodigo("CoD1G0");
		usuario.setNifNie("43800900R");
		usuario.setEmail("JohnDoe1Doe2prueba@llamaloX.com");
		usuario.setPassword("1234");
		usuario.setRol(rol);
		usuario.setIntentos(intentos);
		return usuario;		
	}
		
	/**
	 * 
	 * @param id
	 * @param codigo
	 * @param descripcion
	 * @return
	 */
	public static Rol crearRol(Integer id, String codigo, String descripcion) {
		Rol rol = new Rol();
		rol.setId(id);
		rol.setCodigo(codigo);
		rol.setDescripcion(descripcion);
		return rol;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Set<Rol> crearRoles() {
		Rol rol1 = crearRol(1, "Rol1", "Descripcion 1");
		Rol rol2 = crearRol(2, "Rol2", "Descripcion 2");
		
		Set<Rol> roles = new HashSet<Rol>();
		roles.add(rol2);
		roles.add(rol1);

		return roles;
	}
	
	public static Pregunta crearPregunta(Integer id, String codigo, String texto) {
		Pregunta pregunta = new Pregunta();
		pregunta.setId(id);
		pregunta.setCodigo(codigo);
		pregunta.setTexto(texto);
		return pregunta;
	}
	/**
	 * 
	 * @return
	 */
	public static List<Pregunta> crearPreguntas() {
		Pregunta pregunta1 = crearPregunta(1, "pregunta1", "Ejemplo pregunta 1");
		Pregunta pregunta2 = crearPregunta(2, "pregunta2", "Ejemplo pregunta 2");
		
		List<Pregunta> preguntas = new ArrayList<Pregunta>();
		preguntas.add(pregunta1);
		preguntas.add(pregunta2);

		return preguntas;
	}
	
	/**
	 * 
	 * @param id
	 * @param codigo
	 * @param correcta
	 * @param texto
	 * @return
	 */
	public static Respuesta crearRespuesta(Integer id, String codigo, Integer correcta, String texto) {
		Respuesta respuesta = new Respuesta();
		respuesta.setId(id);
		respuesta.setCodigo(codigo);
		respuesta.setCorrecta(correcta);
		respuesta.setTexto(texto);
		return respuesta;
	}
	/**
	 * 
	 * @return
	 */
	public static List<Respuesta> crearRespuestas() {
		Respuesta respuesta1 = crearRespuesta(1, "respuesta1", 1, "Ejemplo respuesta 1");
		Respuesta respuesta2 = crearRespuesta(2, "respuesta2", 0, "Ejemplo respuesta 2");
		
		List<Respuesta> respuestas = new ArrayList<Respuesta>();
		respuestas.add(respuesta1);
		respuestas.add(respuesta2);

		return respuestas;
	}
		
	/**
	 * 
	 * @param Id
	 * @param codigo
	 * @param nombre
	 * @param descripcion
	 * @return
	 */
	public static Test crearTest(Integer Id, String codigo, String nombre, String descripcion) {

		Test test = new Test();
		
		test.setId(Id);
		test.setCodigo(codigo);
		test.setNombre(nombre);
		test.setDescripcion(descripcion);
	//	test.setIntentos(intentos);
/*		private Integer oportunidades;
		private Date fechaInicio;
		private Date fechaFin;
		private Date fechaBaja;
		private Set<Pregunta> preguntas;
		private Set<Intento> intentos;
		private Set<Usuario> usuarios;*/
		return test;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Set<Test> crearTests() {
		Set<Test> conjuntoTests = new HashSet<Test>();
		Test test1 = crearTest(1, "Cod1g01", "John", "descripcion del test 1");
		Test test2 = crearTest(1, "Cod1g02", "Jack", "descripcion del test 2");
		conjuntoTests.add(test1);
		conjuntoTests.add(test2);
		return conjuntoTests;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Intento crearIntento(Usuario usuario, Test test){
		Intento intento = new Intento();
		intento.setTest(test);
		intento.setUsuario(usuario);
		return intento;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Set<Intento> crearIntentos(Usuario usuario) {
		Test test1 = crearTest(1, "codigoTest1", "Nombre del Test 1", "Descripción del test 1"); 
		Test test2 = crearTest(2, "codigoTest2", "Nombre del Test 2", "Descripción del test 2"); 
		
		Intento intento1 = crearIntento(usuario, test1);
		Intento intento2 = crearIntento(usuario, test2);
		
		Set<Intento> intentos = new HashSet<Intento>();
		intentos.add(intento1);
		intentos.add(intento2);

		return intentos;
	}
	
	public static Permiso crearPermiso(Integer id, String codigo, String descripcion) {
		Permiso permiso = new Permiso();
		permiso.setId(id);
		permiso.setCodigo(codigo);
		permiso.setDescripcion(descripcion);
		return permiso;
	}
	
	public static Set<Permiso> crearPermisos() {
		Permiso p1 = crearPermiso(1, "Permiso1", "Descripcion 1");
		Permiso p2 = crearPermiso(2, "Permiso2", "Descripcion 2");
		
		Set<Permiso> permisos = new HashSet<Permiso>();
		permisos.add(p1);
		permisos.add(p2);

		return permisos;
	}
}
