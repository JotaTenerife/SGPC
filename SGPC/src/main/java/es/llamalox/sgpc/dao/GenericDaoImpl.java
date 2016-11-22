package es.llamalox.sgpc.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDaoImpl<PK extends Serializable, T> implements
		GenericDao<PK, T> {

	static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);
	private static final String UNCHECKED = "unchecked";

	private final Class<T> persistentClass;

	@SuppressWarnings(UNCHECKED)
	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	@SuppressWarnings(UNCHECKED)
	public T getByKey(PK key) {
		try {
			return (T) getSession().get(persistentClass, key);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	@SuppressWarnings(UNCHECKED)
	public T getByCodigo(String codigo) {
		try {
			Criteria crit = getSession().createCriteria(persistentClass);
			crit.add(Restrictions.eq("codigo", codigo));
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return (T) crit.uniqueResult();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public T save(T entity) {
		try {
			getSession().persist(entity);
			return entity;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	@SuppressWarnings(UNCHECKED)
	public T update(String codigo, T entity) {
		try {
			T original = getByCodigo(codigo);
			original = (T) getSession().merge(entity);
			return original;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public T update(T entity) {
		try {
			getSession().update(entity);
			return entity;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean delete(T entity) {
		try {
			getSession().delete(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteByCodigo(String codigo) {
		try {
			T entity = getByCodigo(codigo);
			Method set = entity.getClass()
					.getMethod("setFechaBaja", Date.class);
			set.invoke(entity, new Date());
			update(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean reactivateByCodigo(String codigo) {
		try {
			T entity = getByCodigo(codigo);
			Field field = entity.getClass().getDeclaredField("fechaBaja");
			field.setAccessible(true);
			field.set(entity, null);
			update(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	@Override
	@SuppressWarnings(UNCHECKED)
	public List<T> findAll() {
		Criteria crit = getSession().createCriteria(persistentClass);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}