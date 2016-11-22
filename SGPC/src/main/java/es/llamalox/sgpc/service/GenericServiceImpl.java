package es.llamalox.sgpc.service;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.llamalox.sgpc.dao.GenericDao;

/**
 * http://www.codesenior.com/en/tutorial/Spring-Generic-DAO-and-Generic-Service-
 * Implementation
 * 
 * @author A640064
 *
 * @param <PK>
 * @param <T>
 */
public abstract class GenericServiceImpl<PK extends Serializable, T> implements
		GenericService<PK, T> {

	static final Logger LOGGER = LoggerFactory
			.getLogger(GenericServiceImpl.class);

	private GenericDao<PK, T> genericDao;

	public GenericServiceImpl(GenericDao<PK, T> genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T getByKey(PK key) {
		return genericDao.getByKey(key);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T getByCodigo(String codigo) {
		return genericDao.getByCodigo(codigo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T save(T entity) {
		return genericDao.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public T update(String codigo, T entity) {
		Method set;
		try {
			set = entity.getClass().getMethod("setCodigo", String.class);
			set.invoke(entity, codigo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return genericDao.update(codigo, entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(T entity) {
		return genericDao.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteByCodigo(String codigo) {
		return genericDao.deleteByCodigo(codigo);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> findAll() {
		return genericDao.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean reactivateByCodigo(String codigo) {
		return genericDao.reactivateByCodigo(codigo);
	}

}
