package es.llamalox.sgpc.service.usuarios;

import java.util.List;

import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.GenericService;

public interface UsuarioService extends GenericService<Integer, Usuario> {

	public List<Test> getTests(Usuario usuario);

}
