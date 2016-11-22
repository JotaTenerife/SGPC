package es.llamalox.sgpc.model.tests.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
public class TestTest {

	private es.llamalox.sgpc.model.tests.Test test;
	
	@Before
	public void setUp() throws Exception {
		test = new es.llamalox.sgpc.model.tests.Test();
	}

	@Test
	public void testGetSetId() {
		Integer id = 21;
		assertNull(test.getId());
		test.setId(id);
		assertEquals(id, test.getId());
	}

	@Test
	public void testGetSetCodigo() {
		String testCodigo = "COD1G0";
		assertNull(test.getCodigo());
		test.setCodigo(testCodigo);
		assertEquals(testCodigo, test.getCodigo());
	}

	@Test
	public void testGetSetNombre() {
		String nombre = "Nombre de Test";
		assertNull(test.getNombre());
		test.setNombre(nombre);
		assertEquals(nombre, test.getNombre());
	}

	@Test
	public void testGetSetDescripcion() {
		String testDescripcion = "Una descripción";
		assertNull(test.getDescripcion());
		test.setDescripcion(testDescripcion) ;
		assertEquals(testDescripcion, test.getDescripcion());
	}

	@Test
	public void testGetSetOportunidades() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetFechaInicio() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetFechaFin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetFechaBaja() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsActivo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetPreguntas() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetIntentos() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		String testText;
		testText = test.toString();
		assertEquals(testText, test.toString());
	}

}
