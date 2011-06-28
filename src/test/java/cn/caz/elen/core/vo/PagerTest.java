/**
 * 
 */
package cn.caz.elen.core.vo;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.caz.elen.core.AbstractTest;

/**
 * @author Cantoraz Zhou
 * 
 */
public class PagerTest extends AbstractTest {

	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(PagerTest.class);

	@Test
	public void testPager() {
		Pager pager = new Pager();
		pager.setTotalResults(0);
		assertEquals(pager.getTotalPages(), 1);

		pager = new Pager();
		pager.setTotalResults(9);
		assertEquals(pager.getTotalPages(), 1);

		pager = new Pager();
		pager.setTotalResults(10);
		assertEquals(pager.getTotalPages(), 1);

		pager = new Pager();
		pager.setTotalResults(11);
		assertEquals(pager.getTotalPages(), 2);

		pager = new Pager();
		pager.setTotalResults(100);
		assertEquals(pager.getTotalPages(), 10);

		pager = new Pager();
		pager.setTotalResults(101);
		assertEquals(pager.getTotalPages(), 11);

		pager = new Pager(20);
		pager.setTotalResults(101);
		assertEquals(pager.getTotalPages(), 6);
	}
}
