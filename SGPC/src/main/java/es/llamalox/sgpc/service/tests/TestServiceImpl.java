package es.llamalox.sgpc.service.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.llamalox.sgpc.dao.GenericDao;
import es.llamalox.sgpc.dao.tests.IntentoDao;
import es.llamalox.sgpc.model.tests.Test;
import es.llamalox.sgpc.service.GenericServiceImpl;

@Service
public class TestServiceImpl extends GenericServiceImpl<Integer, Test>
		implements TestService {

	static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private IntentoDao intentoDao;

	@Autowired
	public TestServiceImpl(GenericDao<Integer, Test> genericDao) {
		super(genericDao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void asignarIntentos(Test test) {
		intentoDao.save(test);
	}

}
