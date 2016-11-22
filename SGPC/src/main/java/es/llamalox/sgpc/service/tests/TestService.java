package es.llamalox.sgpc.service.tests;

import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.service.GenericService;

public interface TestService extends GenericService<Integer, Test> {

	void asignarIntentos(Test test);

}
