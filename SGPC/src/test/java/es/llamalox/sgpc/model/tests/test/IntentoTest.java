/**
 * 
 */
package es.llamalox.sgpc.model.tests.test;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.IntentoId;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.util.MockGenerator;

/**
 * @author JuanJo Yanes
 *
 */
public class IntentoTest {
	
	private static final int ACIERTO_FALSO = 0;
	private static final int ACIERTO_VERDADERO = 1;
	
	private Intento intento;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		intento = new Intento();
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#getId()}.
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#setId(java.lang.Integer)}.
	 */
	@Test
	public void testGetSetPrimaryKey() {
		IntentoId intentoId = new IntentoId();
		assertNotNull(intento.getPrimaryKey());
		intento.setPrimaryKey(intentoId);
		assertEquals(intentoId, intento.getPrimaryKey());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#getUsuario()}.
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#setUsuario(es.llamalox.sgpc.model.usuarios.Usuario)}.
	 */
	@Test
	public void testGetSetUsuario() {
		Usuario usuario = MockGenerator.crearUsuario();
		
		assertNull(intento.getUsuario());
		intento.setUsuario(usuario);
		assertEquals(usuario, intento.getUsuario());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#getTest()}.
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#setTest(es.llamalox.sgpc.model.tests.Test)}.
	 */
	@Test
	public void testGetSetTest() {
		es.llamalox.sgpc.model.tests.Test test = MockGenerator.crearTest(1, "Codigo test 1", "Nombre Test 1", "Descripcion test 1");
		
		assertNull(intento.getTest());
		intento.setTest(test);
		assertEquals(test, intento.getTest());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#getPregunta()}.
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#setPregunta(es.llamalox.sgpc.model.tests.Pregunta)}.
	 */
	@Test
	public void testGetSetPregunta() {
		Pregunta pregunta = MockGenerator.crearPregunta(1, "Codigo pregunta 1", "Texto de la pregunta 1");
		
		assertNull(intento.getPregunta());
		intento.setPregunta(pregunta);
		assertEquals(pregunta, intento.getPregunta());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#getAcierto()}.
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#setAcierto(java.lang.Integer)}.
	 */
	@Test
	public void testGetSetAcierto() {
		Integer acierto = ACIERTO_FALSO;
		
		assertEquals(acierto, intento.getAcierto());
		acierto = ACIERTO_VERDADERO;
		intento.setAcierto(ACIERTO_VERDADERO);
		assertEquals(acierto, intento.getAcierto());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#getIntentos()}.
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#setIntentos(java.lang.Integer)}.
	 */
	@Test
	public void testGetSetIntentos() {
		Integer intentos = 0;
		
		assertEquals(intentos, intento.getIntentos());
		intentos = 10;
		intento.setIntentos(intentos);
		assertEquals(intentos, intento.getIntentos());
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.model.tests.Intento#toString()}.
	 */
	@Test
	public void testToString() {
		es.llamalox.sgpc.model.tests.Test test = MockGenerator.crearTest(1, "Código del Test", "Nombre del Test", "Descripcion del test");
		Usuario usuario = MockGenerator.crearUsuario();
		Intento intentoEjemplo = MockGenerator.crearIntento(usuario,test);
		String intentoText = intentoEjemplo.toString();
		
		assertEquals(intentoText, intentoEjemplo.toString());
		
	}

}
