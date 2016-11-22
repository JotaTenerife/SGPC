/**
 * 
 */
package es.llamalox.sgpc.model.tests.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Respuesta;
import es.llamalox.sgpc.util.MockGenerator;

/**
 * @author JuanJo Yanes
 *
 */
public class PreguntaTest {

	private Pregunta pregunta;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pregunta = new Pregunta();
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Pregunta#getId()}.
	 * Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setId(java.lang.Integer)}.
	 */
	@Test
	public void testGetSetId() {
		Integer id = 21;
		assertNull(pregunta.getId());
		pregunta.setId(id);
		assertEquals(id, pregunta.getId());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Pregunta#getCodigo()}
	 * . Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setCodigo(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetSetCodigo() {
		String testCodigo = "CODIGO";
		assertNull(pregunta.getCodigo());
		pregunta.setCodigo(testCodigo);
		assertEquals(testCodigo, pregunta.getCodigo());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Pregunta#getTexto()}.
	 * Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setTexto(java.lang.String)}.
	 */
	@Test
	public void testGetSetTexto() {
		String textoPregunta = "¿Es un ejemplo de pregunta?";
		assertNull(pregunta.getTexto());
		pregunta.setTexto(textoPregunta);
		assertEquals(textoPregunta, pregunta.getTexto());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Pregunta#getImagen()}
	 * . Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setImagen(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetSetImagen() {
		String rutaImagen = "/src/images";
		assertNull(pregunta.getImagen());
		pregunta.setImagen(rutaImagen);
		assertEquals(rutaImagen, pregunta.getImagen());
	}

	/**
	 * Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#getRespuestas()}. Test
	 * method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setRespuestas(java.util.List)}
	 * .
	 */
	@Test
	public void testGetSetRespuestas() {
		List<Respuesta> respuestas = pregunta.getRespuestas();
		assertNull(respuestas);
		respuestas = MockGenerator.crearRespuestas();
		assertFalse(respuestas.isEmpty());
		pregunta.setRespuestas(respuestas);
		assertEquals(respuestas, pregunta.getRespuestas());
	}

	/**
	 * Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#getFechaBaja()}. Test method
	 * for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setFechaBaja(java.util.Date)}
	 * .
	 */
	@Test
	public void testGetSetFechaBaja() {
		Date fechaBajaDiaActual = new Date();
		assertNull(pregunta.getFechaBaja());
		pregunta.setFechaBaja(null);
		assertNull(pregunta.getFechaBaja());
		pregunta.setFechaBaja(fechaBajaDiaActual);
		assertNotNull(pregunta.getFechaBaja());
		assertEquals(fechaBajaDiaActual, pregunta.getFechaBaja());
	}

	/**
	 * Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#getIntentos()}. Test method
	 * for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#setIntentos(java.util.Set)}.
	 */
	// @Test
	// public void testGetSetIntentos() {
	// Usuario usuarioEjemplo = MockGenerator.crearUsuario();
	// Set<Intento> intentos = MockGenerator.crearIntentos(usuarioEjemplo);
	// assertNull(pregunta.getIntentos());
	// pregunta.setIntentos(intentos);
	// assertEquals(intentos, pregunta.getIntentos());
	// }

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Pregunta#isActivo()}.
	 */
	@Test
	public void testIsActivo() {
		Date fechaActual = new Date();
		assertTrue(pregunta.isActivo());
		pregunta.setFechaBaja(fechaActual);
		assertFalse(pregunta.isActivo());
	}

	/**
	 * Test method for
	 * {@link es.llamalox.sgpc.model.tests.Pregunta#isAsignado()}.
	 */
	// @Test
	// public void testIsAsignado() {
	// Usuario usuario = MockGenerator.crearUsuario();
	// Set<Intento> intentos = MockGenerator.crearIntentos(usuario);
	// assertFalse(pregunta.isAsignado());
	// pregunta.setIntentos(intentos);
	// assertTrue(pregunta.isAsignado());
	// }

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Pregunta#toString()}.
	 */
	@Test
	public void testToString() {
		String preguntaText;
		preguntaText = pregunta.toString();
		assertEquals(preguntaText, pregunta.toString());
	}

}
