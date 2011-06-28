/**
 * 
 */
package cn.caz.elen.core.service.local.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.caz.elen.core.dao.IUserDao;
import cn.caz.elen.core.domain.User;
import cn.caz.elen.core.service.local.IUserService;

/**
 * @author Cantoraz Zhou
 * 
 */
@Service("userService")
public class UserService extends AbstractGenericService<User, IUserDao> implements IUserService {

	private IUserDao userDao;

	/**
	 * @return the userDao
	 */
	public IUserDao getUserDao() {
		return this.userDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.service.local.IUserService#findByEmail(java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findByEmail(String email) {
		return this.dao.findByEmail(email);
	}

}
