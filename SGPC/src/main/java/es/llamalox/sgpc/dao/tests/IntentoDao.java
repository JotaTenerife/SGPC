package es.llamalox.sgpc.dao.tests;

import java.util.List;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;

public interface IntentoDao extends GenericDao<Integer, Intento> {

	public Intento getIntento(String test, String pregunta, String usuario);

	public List<Intento> getIntentos(String test, String usuario);

	public List<Intento> getIntentos(Pregunta pregunta, Usuario usuario);

	public List<Intento> getIntentos(Test test, Usuario usuario);

	public List<Intento> getIntentos(Test test);

	public List<Intento> getIntentos(Pregunta pregunta);

	public void save(Test test);

	public List<Intento> getTestsByUser(Usuario usuario);

	public void limpiaIntentos(Test test, Usuario usuario);
}
