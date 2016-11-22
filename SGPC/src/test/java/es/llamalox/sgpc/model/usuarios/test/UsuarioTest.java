package es.llamalox.sgpc.model.usuarios.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.util.MockGenerator;

public class UsuarioTest {

	private Usuario usuario;
	
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
	}
	
	@Test
	public void testGetSetId() {
		Integer id = 21;
		assertNull(usuario.getId());
		usuario.setId(id);
		assertEquals(id, usuario.getId());	
	}

	@Test
	public void testGetSetCodigo() {
		String codigo = "COD1G0";
		assertNull(usuario.getCodigo());
		usuario.setCodigo(codigo) ;
		assertEquals(codigo, usuario.getCodigo());
	}

	@Test
	public void testGetSetPassword() {
		String password = "Ej3mpl0@Pa55Word";
		assertNull(usuario.getPassword());
		usuario.setPassword(password) ;
		assertEquals(password, usuario.getPassword());
	}

	@Test
	public void testGetSetNifNie() {
		String nifNie = "43818000R";
		assertNull(usuario.getNifNie());
		usuario.setNifNie(nifNie) ;
		assertEquals(nifNie, usuario.getNifNie());
	}

	@Test
	public void testGetSetNombre() {
		String nombre = "John Doe";
		assertNull(usuario.getNombre());
		usuario.setNombre(nombre) ;
		assertEquals(nombre, usuario.getNombre());
	}

	@Test
	public void testGetSetApellido1() {
		String apellido1 = "Doe1";
		assertNull(usuario.getApellido1());
		usuario.setApellido1(apellido1) ;
		assertEquals(apellido1, usuario.getApellido1());
	}

	@Test
	public void testGetSetApellido2() {
		String apellido2 = "Doe2";
		assertNull(usuario.getApellido2());
		usuario.setApellido2(apellido2) ;
		assertEquals(apellido2, usuario.getApellido2());
	}

	@Test
	public void testGetSetEmail() {
		String email = "example@llamaloX.com";
		assertNull(usuario.getEmail());
		usuario.setEmail(email) ;
		assertEquals(email, usuario.getEmail());
	}

	@Test
	public void testGetSetTelefono() {
		String telefono = "+34 555 555 555";
		assertNull(usuario.getTelefono());
		usuario.setTelefono(telefono) ;
		assertEquals(telefono, usuario.getTelefono());
	}

	@Test
	public void testGetSetFechaAlta() {
		Date fechaAltaDiaActual = new Date();
		assertNull(usuario.getFechaAlta());
		usuario.setFechaAlta(null);
		assertNull(usuario.getFechaAlta());
		usuario.setFechaAlta(fechaAltaDiaActual);
		assertNotNull(usuario.getFechaAlta());
		assertEquals(fechaAltaDiaActual, usuario.getFechaAlta());
	}

	@Test
	public void testGetSetFechaBaja() {
		Date fechaBajaDiaActual = new Date();
		assertNull(usuario.getFechaBaja());
		usuario.setFechaBaja(null);
		assertNull(usuario.getFechaBaja());
		usuario.setFechaBaja(fechaBajaDiaActual);
		assertNotNull(usuario.getFechaBaja());
		assertEquals(fechaBajaDiaActual, usuario.getFechaBaja());
	}

	@Test
	public void testIsActivo() {
		Date fechaActual = new Date();
		assertTrue(usuario.isActivo());
		usuario.setFechaBaja(fechaActual);
		assertFalse(usuario.isActivo());
	}

	@Test
	public void testGetSetRol() {
		Rol rol = MockGenerator.crearRol(1, "Rol1", "Descripcion 1");
		usuario.setRol(rol) ;
		assertEquals(rol, usuario.getRol());
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetSetIntentos() {
		Set<Intento> intentos = usuario.getIntentos();
		intentos = MockGenerator.crearIntentos(usuario);
		assertFalse(intentos.isEmpty());
		intentos = MockGenerator.crearIntentos(usuario);
		usuario.setIntentos(intentos);
		assertEquals(intentos, usuario.getIntentos());
	}
	@Test
	public void testgetTests() {
		Usuario ejemploUsuario = MockGenerator.crearUsuario();
		Set<Intento> intentos = MockGenerator.crearIntentos(ejemploUsuario);
		ejemploUsuario.setIntentos(intentos);		
		assertNotNull(ejemploUsuario.getTests());
	}
	
	/**
	 * 
	 */
	@Test 
	public void testToString() {
		String usuarioText;
		usuarioText = usuario.toString();
		assertEquals(usuarioText, usuario.toString());
	}
}
