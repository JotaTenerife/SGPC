package es.llamalox.sgpc.service.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.model.tests.Pregunta;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class PreguntaServiceImpl extends GenericServiceImpl<Integer, Pregunta>
		implements PreguntaService {

	@Autowired
	public PreguntaServiceImpl(GenericDao<Integer, Pregunta> genericDao) {
		super(genericDao);
	}

}
