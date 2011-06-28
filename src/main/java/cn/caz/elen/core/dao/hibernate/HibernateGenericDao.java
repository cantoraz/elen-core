/**
 * 
 */
package cn.caz.elen.core.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.caz.elen.core.dao.IGenericDao;
import cn.caz.elen.core.domain.IElenModel;
import cn.caz.elen.core.vo.Pager;

/**
 * @author Cantoraz Zhou
 * 
 */
public abstract class HibernateGenericDao<E extends IElenModel> extends HibernateDaoSupport implements IGenericDao<E> {

	private Log logger = LogFactory.getLog(super.getClass());

	private Class<E> entityClass;

	/**
	 * Default Constructor
	 */
	@SuppressWarnings("unchecked")
	public HibernateGenericDao() {
		super();
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/**
	 * @return the entityClass
	 */
	public Class<E> getEntityClass() {
		return this.entityClass;
	}

	/**
	 * @param entityClass
	 *            the entityClass to set
	 */
	public void setEntityClass(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#initLazyProperties(java.lang.Object, java.lang.String[])
	 */
	@Override
	public void initLazyProperties(Object object, String... properties) {
		for (String property : properties) {
			try {
				boolean isLeaf = true;
				String subProperties = null;
				if (property.indexOf(".") > 0) {
					isLeaf = false;
					String[] props = property.split("\\.", 2);
					property = props[0];
					subProperties = props[1];
				}
				Method method = object.getClass().getMethod("get" + StringUtils.capitalize(property), new Class[0]);
				Object subObject = method.invoke(object);
				if (!isLeaf) {
					initLazyProperties(subObject, new String[] { subProperties });
				} else if (!Hibernate.isInitialized(subObject)) {
					Hibernate.initialize(subObject);
				}
			} catch (Exception e) {
				logger.error("Failed to initialize properties of proxy entity.", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#save(cn.caz.elen.core.domain .IElenModel)
	 */
	@Override
	public void save(E entity) {
		this.getHibernateTemplate().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#update(cn.caz.elen.core.domain .IElenModel)
	 */
	@Override
	public void update(E entity) {
		this.getHibernateTemplate().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#saveOrUpdate(cn.caz.elen.core .domain.IElenModel)
	 */
	@Override
	public void saveOrUpdate(E entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#delete(cn.caz.elen.core.domain .IElenModel)
	 */
	@Override
	public void delete(E entity) {
		this.getHibernateTemplate().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#deleteAll(java.util.Collection)
	 */
	@Override
	public void deleteAll(Collection<E> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#deleteByIdentifier(java.io.Serializable)
	 */
	@Override
	public void deleteByIdentifier(Serializable identifier) {
		this.getHibernateTemplate().delete(this.findByIdentifier(identifier));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#findByIdentifier(java.io.Serializable)
	 */
	@Override
	public E findByIdentifier(Serializable identifier) {
		return findByIdentifier(identifier, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#findByIdentifier(java.io.Serializable, boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E findByIdentifier(Serializable identifier, boolean cacheable) {
		this.getHibernateTemplate().setCacheQueries(cacheable);
		return (E) this.getHibernateTemplate().get(this.entityClass, identifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#findAll()
	 */
	@Override
	public List<E> findAll() {
		return findAll(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#findAll(boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll(boolean cacheable) {
		this.getHibernateTemplate().setCacheQueries(cacheable);
		return this.getHibernateTemplate().loadAll(this.entityClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#findAll(cn.caz.elen.core.vo.Pager)
	 */
	@Override
	public List<E> findAll(Pager pager) {
		return findAll(pager, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IGenericDao#findAll(cn.caz.elen.core.vo.Pager, boolean)
	 */
	@Override
	public List<E> findAll(final Pager pager, final boolean cacheable) {
		@SuppressWarnings("unchecked")
		List<E> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria critCount = session.createCriteria(entityClass);
				critCount.setCacheable(cacheable);
				Long totalResults = (Long) critCount.setProjection(Projections.rowCount()).uniqueResult();
				pager.setTotalResults(totalResults);

				Criteria critAll = session.createCriteria(entityClass);
				critAll.setCacheable(cacheable);
				critAll.setFirstResult(pager.getFirstResult());
				critAll.setMaxResults(pager.getMaxResults());

				return critAll.list();
			}
		});

		return list;
	}
}
