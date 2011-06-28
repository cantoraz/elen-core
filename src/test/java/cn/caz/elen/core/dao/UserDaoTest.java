/**
 * 
 */
package cn.caz.elen.core.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import cn.caz.elen.core.AbstractTest;
import cn.caz.elen.core.domain.User;

/**
 * @author Cantoraz Zhou
 * 
 */
@SuppressWarnings("unused")
public class UserDaoTest extends AbstractTest {

	private static final String FIRST_NAME = "Cantoraz";

	private static final String LAST_NAME = "Zhou";

	private static final String EMAIL = "cantoraz@gmail.com";

	private static final String PASSWORD = "ichbincaz";

	private static final String PASSWORD_NEW = "123456";

	private static Log logger = LogFactory.getLog(UserDaoTest.class);

	@Autowired()
	private IUserDao userDao;

	@Test
	public void testSave() {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);

		assertNull(user.getId());

		try {
			this.userDao.save(user);
			Assert.assertNotNull(user.getId());
		} catch (DataAccessException e) {
			logger.debug("skip duplicate entry.");
		}
	}

	@Test
	public void testFindByEmail() {
		User user = this.userDao.findByEmail(EMAIL);

		assertEquals(user.getEmail(), EMAIL);
		assertEquals(user.getFirstName(), FIRST_NAME);
		assertEquals(user.getLastName(), LAST_NAME);
	}

	@Test
	public void testSaveOrUpdate() {
		User user = this.userDao.findByEmail(EMAIL);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Date newModifyTime = Calendar.getInstance().getTime();
		user.setModifyTime(newModifyTime);

		this.userDao.saveOrUpdate(user);

		user = this.userDao.findByEmail(EMAIL);

		assertEquals(user.getModifyTime().getTime() / 1000, newModifyTime.getTime() / 1000);
	}

	@Test
	public void testUpdate() {
		User user = this.userDao.findByEmail(EMAIL);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Date newModifyTime = Calendar.getInstance().getTime();
		user.setModifyTime(newModifyTime);

		this.userDao.saveOrUpdate(user);

		user = this.userDao.findByEmail(EMAIL);

		assertEquals(user.getModifyTime().getTime() / 1000, newModifyTime.getTime() / 1000);
	}

	@Test
	public void testFindByIdentifier() {
		User user = this.userDao.findByEmail(EMAIL);

		User user2 = this.userDao.findByIdentifier(user.getId());

		assertEquals(user.getEmail(), user2.getEmail());
	}

	@Test
	public void testDelete() {
		User user = this.userDao.findByEmail(EMAIL);

		assertNotNull(user);

		this.userDao.delete(user);

		user = this.userDao.findByEmail(EMAIL);

		assertNull(user);
	}

	@Test
	public void testFindAll() {
		List<User> users = this.userDao.findAll();

		assertTrue(users.isEmpty());
	}

}
