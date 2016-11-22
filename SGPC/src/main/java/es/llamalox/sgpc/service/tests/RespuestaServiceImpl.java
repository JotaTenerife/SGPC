package es.llamalox.sgpc.service.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.model.tests.Respuesta;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class RespuestaServiceImpl extends
		GenericServiceImpl<Integer, Respuesta> implements RespuestaService {

	@Autowired
	public RespuestaServiceImpl(GenericDao<Integer, Respuesta> genericDao) {
		super(genericDao);
	}

}
