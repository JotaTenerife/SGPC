package es.llamalox.sgpc.service.usuarios;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.dao.usuarios.UsuarioDao;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Integer, Usuario>
		implements UsuarioService {

	static final Logger LOGGER = LoggerFactory
			.getLogger(UsuarioServiceImpl.class);

	private UsuarioDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UsuarioServiceImpl(GenericDao<Integer, Usuario> genericDao) {
		super(genericDao);
		this.dao = (UsuarioDao) genericDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Usuario save(Usuario usuario) {
		usuario.setFechaAlta(new Date());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return dao.save(usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Usuario update(String codigo, Usuario usuario) {
		Usuario usuarioOriginal = dao.getByCodigo(codigo);
		usuario.setCodigo(codigo);
		if (!usuarioOriginal.getPassword().equals(usuario.getPassword())) {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		}
		return dao.update(codigo, usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Test> getTests(Usuario usuario) {
		return dao.getTests(usuario);
	}

}