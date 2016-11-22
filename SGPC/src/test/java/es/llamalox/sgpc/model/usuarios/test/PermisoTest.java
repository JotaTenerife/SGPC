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

public class PermisoTest {
	
	private Permiso permiso;
	
		
	@Before
	public void setUp() throws Exception {
		permiso = new Permiso();
	}

	@Test
	public void testGetSetId() {
		Integer permisoId = 5;
		assertNull(permiso.getId());
		permiso.setId(permisoId);
		assertEquals(permisoId, permiso.getId());
	}

	@Test
	public void testGetSetCodigo() {
		String testCodigo = "COD1G0";
		assertNull(permiso.getCodigo());
		permiso.setCodigo(testCodigo) ;
		assertEquals(testCodigo, permiso.getCodigo());
	}

	@Test
	public void testGetSetDescripcion() {
		String testDescripcion = "Una descripción";
		assertNull(permiso.getDescripcion());
		permiso.setDescripcion(testDescripcion) ;
		assertEquals(testDescripcion, permiso.getDescripcion());
	}

	@Test
	public void testGetSetRoles() {
		Set<Rol> testRoles = permiso.getRoles();
		assertNull(testRoles);
		testRoles = MockGenerator.crearRoles();
		assertFalse(testRoles.isEmpty());
		testRoles = MockGenerator.crearRoles();
		permiso.setRoles(testRoles);
		assertEquals(testRoles, permiso.getRoles());
	}

	@Test
	public void testGetSetFechaBaja() {
		Date fechaActual = new Date();
		
		assertNull(permiso.getFechaBaja());
		permiso.setFechaBaja(fechaActual);
	}

	@Test
	public void testIsActivo() {
		assertTrue(permiso.isActivo());	
	}
	
	@Test 
	public void testToString() {
		String permisoText;
		permisoText = permiso.toString();
		assertEquals(permisoText, permiso.toString());
	}
}
