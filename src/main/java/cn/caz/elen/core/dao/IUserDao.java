/**
 * 
 */
package cn.caz.elen.core.dao;

import cn.caz.elen.core.domain.User;

/**
 * @author Cantoraz Zhou
 * 
 */
public interface IUserDao extends IGenericDao<User> {

	public User findByEmail(final String email);

}
