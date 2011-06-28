/**
 * 
 */
package cn.caz.elen.core.service.local;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.caz.elen.core.dao.IGenericDao;
import cn.caz.elen.core.domain.IElenModel;
import cn.caz.elen.core.vo.Pager;

/**
 * @author Cantoraz Zhou
 * 
 */
public interface IGenericService<E extends IElenModel, D extends IGenericDao<E>> {

	public abstract void save(E entity);

	public abstract void update(E entity);

	public abstract void saveOrUpdate(E entity);

	public abstract void delete(E entity);

	public abstract void deleteAll(Collection<E> entities);

	public abstract void deleteByIdentifier(Serializable identifier);

	public abstract E findByIdentifier(Serializable identifier);

	public abstract List<E> findAll(Pager pager);
}
