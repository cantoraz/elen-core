/**
 * 
 */
package cn.caz.elen.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.caz.elen.core.domain.IElenModel;
import cn.caz.elen.core.vo.Pager;

/**
 * @author Cantoraz Zhou
 * 
 */
public interface IGenericDao<E extends IElenModel> {

	public abstract void initLazyProperties(Object object, String... properties);

	public abstract void save(E entity);

	public abstract void update(E entity);

	public abstract void saveOrUpdate(E entity);

	public abstract void delete(E entity);

	public abstract void deleteAll(Collection<E> entities);

	public abstract void deleteByIdentifier(Serializable identifier);

	public abstract E findByIdentifier(Serializable identifier);

	public abstract E findByIdentifier(Serializable identifier, boolean cacheable);

	public abstract List<E> findAll();

	public abstract List<E> findAll(boolean cacheable);

	public abstract List<E> findAll(Pager pager);

	public abstract List<E> findAll(Pager pager, boolean cacheable);
}
