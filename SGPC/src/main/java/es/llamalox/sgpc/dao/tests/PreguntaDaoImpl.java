package es.llamalox.sgpc.dao.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.model.tests.Respuesta;

@Repository
public class PreguntaDaoImpl extends GenericDaoImpl<Integer, Pregunta>
		implements PreguntaDao {

	static final Logger LOGGER = LoggerFactory.getLogger(PreguntaDaoImpl.class);

	@Autowired
	private RespuestaDao respuestaDao;

	/**
	 * Guarda una pregunta con las respuestas asociadas.
	 */
	@Override
	public Pregunta save(Pregunta pregunta) {
		try {
			getSession().persist(pregunta);
			for (Respuesta respuesta : pregunta.getRespuestas()) {
				respuesta.setPregunta(pregunta);
				respuestaDao.save(respuesta);
			}
			return pregunta;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Actualiza una pregunta y sus respuestas. Si la respuesta ya existía y el
	 * texto está vacío, la borra. Si la respuesta ya existía y el texto está
	 * cambiado, la actualiza. Si la respuesta no existía, la crea.
	 */
	@Override
	public Pregunta update(String codigo, Pregunta pregunta) {
		try {
			Pregunta original = getByCodigo(codigo);
			for (Respuesta respuesta : pregunta.getRespuestas()) {
				Respuesta respuestaOriginal = respuestaDao
						.getByCodigo(respuesta.getCodigo());
				if (respuestaOriginal != null) {
					if (respuesta.getTexto() == null) {
						respuestaDao.delete(respuestaOriginal);
					} else {
						respuestaOriginal.setTexto(respuesta.getTexto());
						respuestaOriginal.setCorrecta(respuesta.getCorrecta());
					}
				} else {
					if (respuesta.getTexto() != null) {
						respuesta.setPregunta(pregunta);
						respuestaDao.save(respuesta);
					}
				}
			}
			original = (Pregunta) getSession().merge(pregunta);
			return original;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

}
