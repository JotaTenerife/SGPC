package es.llamalox.sgpc.dao.tests;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.dao.usuarios.UsuarioDao;
import es.llamalox.sgpc.model.tests.Intento;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.model.usuarios.Usuario;

@Repository
public class IntentoDaoImpl extends GenericDaoImpl<Integer, Intento> implements
		IntentoDao {

	@Autowired
	private TestDao testDao;

	@Autowired
	private PreguntaDao preguntaDao;

	@Autowired
	private UsuarioDao usuarioDao;

	static final Logger LOGGER = LoggerFactory.getLogger(IntentoDaoImpl.class);

	/**
	 * Al intentar guardar un intento inicializa todos los intentos asociados a
	 * las preguntas de ese test para ese usuario.
	 */
	@Override
	public void save(Test test) {
		Test testAntiguo = testDao.getByCodigo(test.getCodigo());
		for (Usuario usuario : testAntiguo.getUsuarios()) {
			if (!test.getUsuarios().contains(usuario)) {
				deleteIntentos(testAntiguo, usuario);
			}
		}
		for (Usuario usuario : test.getUsuarios()) {
			try {
				for (Pregunta pregunta : testAntiguo.getPreguntas()) {
					Intento intentoCalculado = new Intento();
					intentoCalculado.setUsuario(usuario);
					intentoCalculado.setPregunta(pregunta);
					intentoCalculado.setTest(test);
					intentoCalculado.setAcierto(0);
					intentoCalculado.setIntentos(0);
					getSession().persist(intentoCalculado);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	@Override
	public Intento getIntento(String test, String pregunta, String usuario) {
		Criteria crit = getSession().createCriteria(Intento.class);
		crit.add(Restrictions.eq("primaryKey.test", testDao.getByCodigo(test)));
		crit.add(Restrictions.eq("primaryKey.usuario",
				usuarioDao.getByCodigo(usuario)));
		crit.add(Restrictions.eq("primaryKey.pregunta",
				preguntaDao.getByCodigo(pregunta)));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Intento) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intento> getIntentos(Pregunta pregunta, Usuario usuario) {
		Criteria crit = getSession().createCriteria(Intento.class);
		crit.add(Restrictions.eq("primaryKey.pregunta", pregunta));
		crit.add(Restrictions.eq("primaryKey.usuario", usuario));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intento> getIntentos(Test test, Usuario usuario) {
		Criteria crit = getSession().createCriteria(Intento.class);
		crit.add(Restrictions.eq("primaryKey.test", test));
		crit.add(Restrictions.eq("primaryKey.usuario", usuario));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Intento> getIntentos(Test test) {
		Criteria crit = getSession().createCriteria(Intento.class);
		crit.add(Restrictions.eq("primaryKey.test", test));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	public void deleteIntentos(Test test, Usuario usuario) {
		for (Intento intento : getIntentos(test, usuario)) {
			getSession().delete(intento);
		}
	}

	@Override
	public List<Intento> getTestsByUser(Usuario usuario) {
		return null;
	}

	@Override
	public Intento update(Intento intento) {
		String test = intento.getTest().getCodigo();
		String pregunta = intento.getPregunta().getCodigo();
		String usuario = intento.getUsuario().getCodigo();
		Intento intentoAntiguo = getIntento(test, pregunta, usuario);
		intentoAntiguo.setAcierto(intento.getAcierto());
		intentoAntiguo.setIntentos(intentoAntiguo.getIntentos() + 1);
		getSession().persist(intentoAntiguo);
		return intentoAntiguo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Intento> getIntentos(String test, String usuario) {
		Criteria crit = getSession().createCriteria(Intento.class);
		crit.add(Restrictions.eq("primaryKey.test", testDao.getByCodigo(test)));
		crit.add(Restrictions.eq("primaryKey.usuario",
				usuarioDao.getByCodigo(usuario)));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	@Override
	public void limpiaIntentos(Test test, Usuario usuario) {
		List<Intento> intentos = getIntentos(test, usuario);
		int minIntento = intentos.get(0).getIntentos();
		for (Intento intento : intentos) {
			if (intento.getIntentos() < minIntento) {
				minIntento = intento.getIntentos();
			}
		}
		for (Intento intento : intentos) {
			intento.setIntentos(minIntento);
			getSession().persist(intento);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intento> getIntentos(Pregunta pregunta) {
		Criteria crit = getSession().createCriteria(Intento.class);
		crit.add(Restrictions.eq("primaryKey.pregunta", pregunta));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
}
