/**
 * 
 */
package cn.caz.elen.core.service.local.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.caz.elen.core.dao.IGenericDao;
import cn.caz.elen.core.domain.IElenModel;
import cn.caz.elen.core.service.local.IGenericService;
import cn.caz.elen.core.vo.Pager;

/**
 * @author Cantoraz Zhou
 * 
 */
public class AbstractGenericService<E extends IElenModel, D extends IGenericDao<E>> implements IGenericService<E, D> {

	protected D dao;

	/**
	 * @return the dao
	 */
	public D getDao() {
		return this.dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(D dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#save(cn.caz.elen.core.domain.IElenModel)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(E entity) {
		this.dao.save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#update(cn.caz.elen.core.domain.IElenModel)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(E entity) {
		this.dao.update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#saveOrUpdate(cn.caz.elen.core.domain.IElenModel)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdate(E entity) {
		this.dao.saveOrUpdate(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#delete(cn.caz.elen.core.domain.IElenModel)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(E entity) {
		this.dao.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#deleteAll(java.util.Collection)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll(Collection<E> entities) {
		this.dao.deleteAll(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#deleteByIdentifier(java.io.Serializable)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteByIdentifier(Serializable identifier) {
		this.dao.deleteByIdentifier(identifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#findByIdentifier(java.io.Serializable)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public E findByIdentifier(Serializable identifier) {
		return this.dao.findByIdentifier(identifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IGenericService#findAll(cn.caz.elen.core.vo.Pager)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<E> findAll(Pager pager) {
		return this.dao.findAll(pager);
	}

}
