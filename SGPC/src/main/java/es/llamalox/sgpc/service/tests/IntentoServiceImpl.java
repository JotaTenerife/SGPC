package es.llamalox.sgpc.service.tests;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.dao.tests.IntentoDao;
import es.llamalox.sgpc.dao.tests.TestDao;
import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class IntentoServiceImpl extends GenericServiceImpl<Integer, Intento>
		implements IntentoService {

	static final Logger LOGGER = LoggerFactory
			.getLogger(IntentoServiceImpl.class);

	@Autowired
	private IntentoDao dao;

	@Autowired
	private TestDao testDao;

	@Autowired
	public IntentoServiceImpl(GenericDao<Integer, Intento> genericDao) {
		super(genericDao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Intento save(Intento intento) {
		return dao.save(intento);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Intento getIntento(String test, String pregunta, String usuario) {
		return dao.getIntento(test, pregunta, usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Intento update(Intento intento) {
		return dao.update(intento);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public boolean isAprobado(String test, String usuario) {
		int sum = 0;
		Test testObjeto = testDao.getByCodigo(test);
		List<Intento> intentos = dao.getIntentos(test, usuario);
		if (intentos.size() > 0) {
			for (Intento intento : dao.getIntentos(test, usuario)) {
				sum += intento.getAcierto();
			}
		}
		if (testObjeto.getPreguntas().size() == sum) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Intento> getIntentos(Pregunta pregunta, Usuario usuario) {
		return dao.getIntentos(pregunta, usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Intento> getIntentos(Test test, Usuario usuario) {
		return dao.getIntentos(test, usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void limpiaIntentos(Test test, Usuario usuario) {
		dao.limpiaIntentos(test, usuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Intento> getIntentos(Test test) {
		return dao.getIntentos(test);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Intento> getIntentos(Pregunta pregunta) {
		return dao.getIntentos(pregunta);
	}
}
