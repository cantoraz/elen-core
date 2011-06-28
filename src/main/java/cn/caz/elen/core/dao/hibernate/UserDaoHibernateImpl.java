/**
 * 
 */
package cn.caz.elen.core.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.caz.elen.core.dao.IUserDao;
import cn.caz.elen.core.domain.User;

/**
 * @author Cantoraz Zhou
 * 
 */
@Repository("userDao")
public class UserDaoHibernateImpl extends HibernateGenericDao<User> implements IUserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.caz.elen.core.dao.IUserDao#findByEmail(java.lang.String)
	 */
	public User findByEmail(final String email) {
		@SuppressWarnings("unchecked")
		List<User> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(User.class).add(Restrictions.eq("email", email)).list();
			}
		});

		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
