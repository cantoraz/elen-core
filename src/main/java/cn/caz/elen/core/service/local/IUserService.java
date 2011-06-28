/**
 * 
 */
package cn.caz.elen.core.service.local;

import cn.caz.elen.core.dao.IUserDao;
import cn.caz.elen.core.domain.User;

/**
 * @author Cantoraz Zhou
 * 
 */
public interface IUserService extends IGenericService<User, IUserDao> {

	public User findByEmail(String email);

}
