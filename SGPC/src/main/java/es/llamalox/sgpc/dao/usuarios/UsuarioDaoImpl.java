package es.llamalox.sgpc.dao.usuarios;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.dao.tests.IntentoDao;
import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;

@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Integer, Usuario> implements
		UsuarioDao {

	@Autowired
	private IntentoDao intentoDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getTests(Usuario usuario) {
		Query query = getSession()
				.createSQLQuery(
						"select distinct i.primaryKey.test from i where i.primaryKey.usuario = :usuarioId")
				.addEntity(Intento.class)
				.setParameter("usuarioId", usuario.getId());
		return query.list();
	}
}