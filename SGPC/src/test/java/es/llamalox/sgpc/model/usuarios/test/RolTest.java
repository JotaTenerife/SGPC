package es.llamalox.sgpc.model.usuarios.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import es.llamalox.sgpc.model.usuarios.Permiso;
import es.llamalox.sgpc.model.usuarios.Rol;
import es.llamalox.sgpc.util.MockGenerator;

public class RolTest {

	private Rol rol;

	@Before
	public void setUp() throws Exception {
		rol = new Rol();
	}
	
	@Test
	public void testGetSetId() {
		Integer testId = 21;
		assertNull(rol.getId());
		rol.setId(testId);
		assertEquals(testId, rol.getId());
	}

	@Test
	public void testGetSetCodigo() {
		String testCodigo = "CODIGO";
		assertNull(rol.getCodigo());
		rol.setCodigo(testCodigo);
		assertEquals(testCodigo, rol.getCodigo());
	}

	@Test
	public void testGetSetDescripcion() {
		String testDescripcion = "Una descripción";
		assertNull(rol.getDescripcion());
		rol.setDescripcion(testDescripcion) ;
		assertEquals(testDescripcion, rol.getDescripcion());
	}

	@Test
	public void testGetSetPermisos() {
		Set<Permiso> testPermisos = rol.getPermisos();
		assertNull(testPermisos);
		testPermisos = MockGenerator.crearPermisos();
		assertFalse(testPermisos.isEmpty());
		testPermisos = MockGenerator.crearPermisos();
		rol.setPermisos(testPermisos);
		assertEquals(testPermisos, rol.getPermisos());
	}
	
	@Test 
	public void testGetSetFechaBaja() {
		Date fechaBajaDiaActual = new Date();
		assertNull(rol.getFechaBaja());
		rol.setFechaBaja(null);
		assertNull(rol.getFechaBaja());
		rol.setFechaBaja(fechaBajaDiaActual);
		assertNotNull(rol.getFechaBaja());
		assertEquals(fechaBajaDiaActual, rol.getFechaBaja());
	}
	
	@Test 
	public void testIsActivo() {
		Date fechaActual = new Date();
		assertTrue(rol.isActivo());
		rol.setFechaBaja(fechaActual);
		assertFalse(rol.isActivo());
	}
	
	@Test 
	public void testToString() {
		String rolText = rol.toString();
		assertEquals(rolText, rol.toString());
	}
}
