/**
 * 
 */
package es.llamalox.sgpc.service.usuarios.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.usuarios.UsuarioServiceImpl;
import es.llamalox.sgpc.util.MockGenerator;

/**
 * @author JuanJo Yanes
 *
 */
public class UsuarioServiceImplTest {

	
	UsuarioServiceImpl usuarioService;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Test method for {@link es.llamalox.sgpc.service.usuarios.UsuarioServiceImpl#UsuarioServiceImpl(es.llamalox.sgpc.dao.GenericDao)}.
	 */
	@Test
	public void testUsuarioServiceImpl() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.service.usuarios.UsuarioServiceImpl#save(es.llamalox.sgpc.model.usuarios.Usuario)}.
	 */
	@Test
	public void testSaveUsuario() {
		Usuario usuario = MockGenerator.crearUsuario();
		
		
		
		
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.service.usuarios.UsuarioServiceImpl#update(java.lang.String, es.llamalox.sgpc.model.usuarios.Usuario)}.
	 */
	@Test
	public void testUpdateStringUsuario() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link es.llamalox.sgpc.service.usuarios.UsuarioServiceImpl#getTests(es.llamalox.sgpc.model.usuarios.Usuario)}.
	 */
	@Test
	public void testGetTests() {
		fail("Not yet implemented");
	}

}
