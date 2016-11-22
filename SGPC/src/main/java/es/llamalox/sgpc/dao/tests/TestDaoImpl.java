package es.llamalox.sgpc.dao.tests;

import org.springframework.stereotype.Repository;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.model.tests.Test;

@Repository
public class TestDaoImpl extends GenericDaoImpl<Integer, Test> implements
		TestDao {

}
