package es.llamalox.sgpc.service.tests;

import java.util.List;

import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.GenericService;

public interface IntentoService extends GenericService<Integer, Intento> {

	public Intento update(Intento intento);

	public List<Intento> getIntentos(Pregunta pregunta, Usuario usuario);

	public List<Intento> getIntentos(Test test, Usuario usuario);

	public List<Intento> getIntentos(Test test);

	public List<Intento> getIntentos(Pregunta pregunta);

	public Intento getIntento(String test, String pregunta, String usuario);

	public void limpiaIntentos(Test test, Usuario usuario);

	public boolean isAprobado(String test, String usuario);

}
