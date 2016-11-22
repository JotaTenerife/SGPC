/**
 * 
 */
package es.llamalox.sgpc.model.usuarios.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.llamalox.sgpc.model.usuarios.PersistentLogin;

public class PersistentLoginTest {

	PersistentLogin persistentLogin;
	
	@Before
	public void setUp() throws Exception {
		persistentLogin = new PersistentLogin();
	}

	@Test
	public void testSetGetSeries() {
		String testSeries = "EjemplodeSeries123456";
		assertNull(persistentLogin.getSeries());
		persistentLogin.setSeries(testSeries) ;
		assertEquals(testSeries, persistentLogin.getSeries());
	}

	@Test
	public void testGetSetUsername() {
		String testUserName = "John Doe";
		assertNull(persistentLogin.getUsername());
		persistentLogin.setUsername(testUserName) ;
		assertEquals(testUserName, persistentLogin.getUsername());
	}

	@Test
	public void testGetSetToken() {
		String testToken = "EjemploDeToken12345676";
		assertNull(persistentLogin.getToken());
		persistentLogin.setToken(testToken) ;
		assertEquals(testToken, persistentLogin.getToken());
	}
	
	@Test
	public void testGetSetLastUsed() {
		Date fechaBajaDiaActual = new Date();
		assertNull(persistentLogin.getLastUsed());
		persistentLogin.setLastUsed(fechaBajaDiaActual);
		assertNotNull(persistentLogin.getLastUsed());
		assertEquals(fechaBajaDiaActual, persistentLogin.getLastUsed());
	}
	
}
