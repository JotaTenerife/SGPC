package es.llamalox.sgpc.dao.usuarios;

import java.util.List;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;

public interface UsuarioDao extends GenericDao<Integer, Usuario> {

	public List<Test> getTests(Usuario usuario);

}
