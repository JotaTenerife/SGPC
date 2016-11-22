package es.llamalox.sgpc.dao.tests;

import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.model.tests.Respuesta;

@Repository
public class RespuestaDaoImpl extends GenericDaoImpl<Integer, Respuesta>
		implements RespuestaDao {

}
